package com.hd.api.service.impl;

import com.hd.api.constant.CodeEnum;
import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.constant.UserInfoConst;
import com.hd.api.constant.VerifyCodeEnum;
import com.hd.api.dao.LoginUserMapper;
import com.hd.api.dto.LoginDTO;
import com.hd.api.exception.ServiceException;
import com.hd.api.manager.TokenManager;
import com.hd.api.model.LoginUser;
import com.hd.api.model.OrgInfo;
import com.hd.api.model.Token;
import com.hd.api.model.Userinfo;
import com.hd.api.service.LoginUserService;
import com.hd.api.service.OrgInfoService;
import com.hd.api.service.UserInfoService;
import com.hd.api.service.VerifyCodeService;
import com.hd.api.thirdly.SMSManager;
import com.hd.api.utils.common.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class LoginUserServiceImpl extends BaseServiceImpl<LoginUser> implements LoginUserService {

    @Autowired
    private LoginUserMapper luMapper;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private OrgInfoService oiService;

    @Autowired
    private VerifyCodeService vcService;

    @Autowired
    private UserInfoService uiService;

    @Autowired
    private SMSManager smsManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LoginDTO login(String phone, String pwd, String cid) {
        LoginUser loginUser = luMapper.selectByPhone(phone);

        checkUserAndPwd(pwd, loginUser);

        // 清空之前用户绑定的cid，用于卸载后登录
        if (StringUtils.isNotEmpty(cid)) {
            luMapper.clearClientId(cid);
        }

        String token = tokenManager.create(loginUser.getUserId());

        loginUser.setLastTime(new Date());

        loginUser.setClientId(cid);
        luMapper.updateByPrimaryKeySelective(loginUser);

        LoginDTO dto = new LoginDTO();
        dto.setUserId(loginUser.getUserId());
        dto.setToken(token);

        return dto;
    }

    /**
     * 检查用户和密码
     *
     * @param pwd
     * @param loginUser
     */
    public void checkUserAndPwd(String pwd, LoginUser loginUser) {
        if (loginUser == null || !pwd.equals(loginUser.getPassWord())) {
            throw new ServiceException(CodeEnum.REQUEST_ERROR, ErrorInfoConst.PASSWORD_WRONG);
        }
    }

    /**
     * 检查验证码是否合法
     *
     * @param phone
     */
    private void checkVerifyCode(String phone, VerifyCodeEnum type, String vcode) {
        if (!vcService.verify(phone, type, vcode)) {
            throw new ServiceException(ErrorInfoConst.VC_WRONG);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void signUpCompany(String inviteCode, String orgName, String userName, String phone, String verifyCode,
                              String passWord) {
        OrgInfo groupOi = null;
        OrgInfo oi = oiService.getByOrgName(orgName);
        if (oi != null) {
            throw new ServiceException(ErrorInfoConst.ORG_REGISTERED);
        }

        Userinfo ui = uiService.getUserInfoByPhone(phone);
        if (ui != null) {
            throw new ServiceException(ErrorInfoConst.USER_REGISTERED);
        }

        checkVerifyCode(phone, VerifyCodeEnum.SIGN_UP, verifyCode);

        // 生成邀请码 存在并发
        String orgInviteCode = oiService.getNewInviteCode();

        // 添加企业信息
        OrgInfo newOi = new OrgInfo();
        newOi.setOrgId(CommonUtil.createUUID());
        newOi.setOrgName(orgName);
        newOi.setCreateTime(new Date());
        newOi.setStatus(0);
        newOi.setInviteCode(orgInviteCode);
        if (groupOi != null) {
            newOi.setSuperiorId(groupOi.getOrgId());
        }

        // 添加用户信息
        Userinfo newUi = new Userinfo();
        newUi.setPhone(phone);
        newUi.setUserid(CommonUtil.createUUID());
        newUi.setCreatetime(new Date());
        newUi.setUsername(userName);
        newUi.setOrgid(newOi.getOrgId());
        // 公司管理员
        newUi.setLevel(UserInfoConst.LevelEnum.MANAGER.toString());

        // 添加登录用户信息
        LoginUser newLu = new LoginUser();
        newLu.setPhone(phone);
        newLu.setStatus(0);
        newLu.setPassWord(passWord);
        newLu.setUserId(newUi.getUserid());
        newLu.setLoginName(userName);
        // newLu.setLoginType(1);

        // 设置公司的创建人
        newOi.setUserId(newUi.getUserid());

        oiService.insert(newOi);
        uiService.insert(newUi);
        luMapper.insert(newLu);

        //使验证码失效
        vcService.expire(phone, VerifyCodeEnum.SIGN_UP);

        // 注册成功，发送注册邀请码
        Boolean result = smsManager.sendInviteCode(String.valueOf(orgInviteCode), orgName, phone);

        Assert.isTrue(result, ErrorInfoConst.IC_SEND_ERROR);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void signUpPerson(String inviteCode, String userName, String phone, String verifyCode, String passWord) {
        OrgInfo oi = oiService.getByInviteCode(inviteCode, null);
        if (oi == null) {
            throw new ServiceException(ErrorInfoConst.IC_WRONG_PERSON);
        }

        Userinfo ui = uiService.getUserInfoByPhone(phone);
        if (ui != null) {
            throw new ServiceException(ErrorInfoConst.USER_REGISTERED);
        }

        checkVerifyCode(phone, VerifyCodeEnum.SIGN_UP, verifyCode);

        // 添加用户信息
        Userinfo newUi = new Userinfo();
        newUi.setPhone(phone);
        newUi.setUserid(CommonUtil.createUUID());
        newUi.setCreatetime(new Date());
        newUi.setUsername(userName);
        newUi.setOrgid(oi.getOrgId());
        // 普通操作员
        newUi.setLevel(UserInfoConst.LevelEnum.OPERATOR.toString());
        uiService.insert(newUi);

        // 添加登录用户信息
        LoginUser newLu = new LoginUser();
        newLu.setPhone(phone);
        newLu.setStatus(0);
        newLu.setPassWord(passWord);
        newLu.setUserId(newUi.getUserid());
        newLu.setLoginName(userName);
        // newLu.setLoginType(0);
        luMapper.insertSelective(newLu);

        // 使验证码失效
        vcService.expire(phone, VerifyCodeEnum.SIGN_UP);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean forgetPwd(String phone, String pwd, String verifyCode) {
        LoginUser lu = luMapper.selectByPhone(phone);

        Assert.notNull(lu, ErrorInfoConst.USER_NOT_EXIST);

        checkVerifyCode(phone, VerifyCodeEnum.REFIND, verifyCode);

        boolean result = luMapper.updatePassWordByUserId(lu.getUserId(), pwd) > 0;
        if (result) {
            // 使验证码失效
            vcService.expire(phone, VerifyCodeEnum.REFIND);
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean logout(String token) {
        Token tokenModel = tokenManager.get(token);
        boolean result = tokenManager.delete(token);
        if (result) {
            result = luMapper.resetClinetId(tokenModel.getUserId()) > 0;
        }
        return result;
    }

    @Override
    public boolean modifyPwd(String userId, String pwd, String newPwd) {
        LoginUser lu = luMapper.selectByPrimaryKey(userId);

        checkUserAndPwd(pwd, lu);

        return luMapper.updatePassWordByUserId(userId, newPwd) > 0;
    }

    @Override
    public boolean login(String phone, String pwd) {
        LoginUser loginUser = luMapper.selectByPhone(phone);

        checkUserAndPwd(pwd, loginUser);

        return true;
    }

    @Override
    public void verifyPhone(String phone) {
        LoginUser user = luMapper.selectByPhone(phone);
        Assert.notNull(user, ErrorInfoConst.PHONE_REGISTERED);
    }

    @Override
    public List<LoginUser> listByOrgId(String orgId) {
        return luMapper.listByOrgId(orgId);
    }
}
