package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.OrgFullInfoDTO;
import com.hd.api.dto.OrgInfoDTO;
import com.hd.api.model.OrgInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgInfoMapper extends BaseMapper<OrgInfo> {

	/**
	 * 根据公司类型查找查找
	 * 
	 * @param branch
	 *            A 集团 B分公司
	 * @return
	 */
	List<OrgInfoDTO> listGroupByBranch(String branch);

	/**
	 * 根据公司名称查找
	 * 
	 * @param orgName
	 * @return
	 */
	OrgInfo getByOrgName(String orgName);

	/**
	 * 根据邀请码查找
	 * 
	 * @param inviteCode
	 * @param branch
	 * @return
	 */
	OrgInfo getByInviteCode(@Param("inviteCode") String inviteCode, @Param("branch") String branch);

	/**
	 * 更新邀请码
	 * 
	 * @param userId
	 * @param inviteCode
	 * @return
	 */
	int updateInviteCode(@Param("userId") String userId, @Param("inviteCode") String inviteCode);

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
	 * 统计分公司数量
	 * 
	 * @param orgId
	 * @return
	 */
	int countSubOrg(String orgId);

	/**
	 * 根据用户ID返回公司详细信息
	 * 
	 * @param userId
	 * @return
	 */
	OrgFullInfoDTO getOrgFullInfoByUserId(@Param("userId") String userId);
}