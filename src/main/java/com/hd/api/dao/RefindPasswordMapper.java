package com.hd.api.dao;

import com.hd.api.model.RefindPassword;
import org.apache.ibatis.annotations.Param;

public interface RefindPasswordMapper {
    
    int getSendCount(String phone);
    
    String getLateVerifyCode(@Param("phone") String phone, @Param("expire") Integer expire);

    RefindPassword selectByPhoneAndCode(@Param("phone") String phone, @Param("code") String code);
}