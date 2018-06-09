package com.hd.api.constant;

/**
 * 返回码常量
 * <p>
 * 0表示成功
 * <p>
 * 1xx表示客户端错误
 * <p>
 * 2xx表示服务端错误
 *
 * @author hoda
 */
public enum CodeEnum {

    /**
     * 执行成功返回
     */
    SUCCESS("0"),

    /**
     * 请求错误返回
     */
    REQUEST_ERROR("100"),

    /**
     * 无效Token
     */
    INVALID_TOKEN("101"),

    /**
     * 用户不存在
     */
    USER_NULL("102"),

    /**
     * 参数错误
     */
    PARAM_ERROR("103"),

    /**
     * 系统异常返回
     */
    SYSTEM_ERROR("200"),

    /**
     * 执行失败返回
     */
    SYSTEM_FAIL("201");

    private String attr;

    CodeEnum(String attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return attr;
    }
}
