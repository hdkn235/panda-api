package com.hd.api.param;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录参数对象
 *
 * @author hoda
 * @since 1.0.0
 */
public class LoginParam {

    @NotEmpty
    private String phone;

    @NotEmpty
    private String password;

    private String clientId;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
