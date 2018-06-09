package com.hd.api.service;

import com.hd.api.dto.OrgFullInfoDTO;
import com.hd.api.model.OrgInfo;

public interface OrgInfoService extends BaseService<OrgInfo> {

    OrgInfo signUpOrgInfo(String inviteCode, String orgName);

    /**
     * 根据公司名称查询
     *
     * @param orgName
     * @return
     */
    OrgInfo getByOrgName(String orgName);

    /**
     * 获取新的邀请码
     *
     * @return
     */
    String getNewInviteCode();

    /**
     * 根据邀请码获取公司信息
     *
     * @param inviteCode
     * @param branch
     * @return
     */
    OrgInfo getByInviteCode(String inviteCode, String branch);

    /**
     * 重置邀请码
     *
     * @param userId 管理员ID
     * @return
     */
    String resetInviteCode(String userId, String pwd);

    /**
     * 根据userID查找组织信息
     *
     * @param userId
     * @return
     */
    OrgInfo getByUserId(String userId);

    /**
     * 根据创建人ID查找组织信息
     *
     * @param userId
     * @return
     */
    OrgInfo getByCreatorId(String userId);

    /**
     * 根据用户ID返回所属公司详细信息
     *
     * @param userId
     * @return
     */
    OrgFullInfoDTO getOrgFullInfoByUserId(String userId);

}
