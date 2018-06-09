package com.hd.api.controller;

import com.hd.api.dto.OrgFullInfoDTO;
import com.hd.api.model.OrgInfo;
import com.hd.api.service.LoginUserService;
import com.hd.api.service.OrgInfoService;
import com.hd.app.security.IgnoreSecurity;
import com.hd.app.bean.Response;
import com.hd.app.param.CompanyParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 公司控制器
 *
 * @author hoda
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private OrgInfoService oiService;

    @Autowired
    private LoginUserService userService;

    /**
     * 注册公司
     *
     * @param param
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(method = RequestMethod.POST)
    public Response signUpCompany(@Valid CompanyParam param) {
        userService.signUpCompany(param.getInviteCode(), param.getOrgName(),
                param.getUserName(), param.getPhone(), param.getVerifyCode(),
                param.getPassWord());
        return new Response().success();
    }

    /**
     * 查看公司详细信息
     *
     * @return
     */
    @RequestMapping(value = "/{orgId}", method = RequestMethod.GET)
    public Response viewCompanyMessage(@PathVariable("orgId") String orgId) {
        String userId = getUserId();
        OrgFullInfoDTO oi = oiService.getOrgFullInfoByUserId(userId);
        return new Response().success(oi);
    }

    /**
     * 更新公司信息
     *
     * @return
     */
    @RequestMapping(value = "/{orgId}", method = RequestMethod.PUT)
    public Response saveCompanyMessage(@PathVariable("orgId") String orgId,
            @RequestParam(value = "orgName", required = false) String orgName,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "remark", required = false) String remark
    ) {
        OrgInfo oi = new OrgInfo();
        oi.setOrgId(orgId);
        oi.setOrgName(orgName);
        oi.setCity(city);
        oi.setLocation(location);
        oi.setRemark(remark);
        oiService.update(oi);
        return new Response().success();
    }
}
