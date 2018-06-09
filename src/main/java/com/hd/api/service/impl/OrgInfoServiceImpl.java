package com.hd.api.service.impl;

import com.hd.api.constant.CodeEnum;
import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.dao.OrgInfoMapper;
import com.hd.api.dto.OrgFullInfoDTO;
import com.hd.api.exception.ServiceException;
import com.hd.api.model.LoginUser;
import com.hd.api.model.OrgInfo;
import com.hd.api.service.LoginUserService;
import com.hd.api.service.OrgInfoService;
import com.hd.utils.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrgInfoServiceImpl extends BaseServiceImpl<OrgInfo> implements OrgInfoService {

    @Autowired
    private OrgInfoMapper oiMapper;

    @Autowired
    private LoginUserService luService;

    @Override
    public OrgInfo signUpOrgInfo(String inviteCode, String orgName) {
        OrgInfo groupOi = null;

        OrgInfo oi = getByOrgName(orgName);
        if (oi != null) {
            throw new ServiceException(ErrorInfoConst.ORG_REGISTERED);
        }

        // 生成邀请码 存在并发
        String orgInviteCode = getNewInviteCode();

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
        oiMapper.insert(newOi);

        return newOi;
    }

    @Override
    public OrgInfo getByOrgName(String orgName) {
        return oiMapper.getByOrgName(orgName);
    }

    @Override
    public String getNewInviteCode() {
        String inviteCode;
        OrgInfo orgInfo;
        do {
            inviteCode = CommonUtil.getInviteCode(6);
            orgInfo = oiMapper.getByInviteCode(inviteCode, null);
        } while (orgInfo != null);
        return inviteCode;
    }

    @Override
    public OrgInfo getByInviteCode(String inviteCode, String branch) {
        return oiMapper.getByInviteCode(inviteCode, branch);
    }

    @Override
    public String resetInviteCode(String userId, String pwd) {
        LoginUser loginUser = luService.get(userId);
        if (!pwd.equals(loginUser.getPassWord())) {
            throw new ServiceException(CodeEnum.USER_NULL, ErrorInfoConst.PASSWORD_WRONG);
        }

        String code = getNewInviteCode();
        int effectCount = oiMapper.updateInviteCode(userId, code);
        if (effectCount > 0) {
            return code;
        }
        return null;
    }

    @Override
    public OrgInfo getByUserId(String userId) {
        return oiMapper.getByUserId(userId);
    }

    @Override
    public OrgInfo getByCreatorId(String userId) {
        return oiMapper.getByCreatorId(userId);
    }

    @Override
    public OrgFullInfoDTO getOrgFullInfoByUserId(String userId) {
        return oiMapper.getOrgFullInfoByUserId(userId);
    }

}
