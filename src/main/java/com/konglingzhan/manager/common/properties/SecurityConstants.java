package com.konglingzhan.manager.common.properties;

public interface SecurityConstants {

    /**
     * 当需要身份认证时，默认跳转的url
     *
     * @see LoginController
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/login/require";

    /**
     * 默认的用户密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL = "/login";

    /**
     * 记住我过期时间
     */
    public static final int rememberMeSeconds = 3600;

}
