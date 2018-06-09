package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.model.LoginUser;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nonnull;
import java.util.List;

public interface LoginUserMapper extends BaseMapper<LoginUser> {
	/**
	 * 根据Userid修改密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	int updatePassWordByUserId(@Param("userId") String userId, @Param("password") String password);

	LoginUser selectByPhone(@Nonnull String phone);

	int clearClientId(@Nonnull String cid);

	int resetClinetId(@Nonnull String userId);
	
	/**
	 * 获取公司所有员工列表
	 * 
	 * @param orgId
	 * @return
	 */
	List<LoginUser> listByOrgId(String orgId);
}