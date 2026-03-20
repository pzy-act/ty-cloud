package com.vip.tycloud.dto.system;

import lombok.Data;

/**
 * 组织与权限 功能模块 - 登录认证 - 登录响应 DTO。
 */
@Data
public class LoginRespDTO {

    /**
     * 访问令牌。
     */
    private String accessToken;

    /**
     * 刷新令牌。
     */
    private String refreshToken;

    /**
     * 令牌类型。
     */
    private String tokenType;

    /**
     * 访问令牌有效期（秒）。
     */
    private Long accessTokenExpireSeconds;

    /**
     * 刷新令牌有效期（秒）。
     */
    private Long refreshTokenExpireSeconds;

    /**
     * 当前登录用户信息。
     */
    private CurrentUserRespDTO userInfo;
}
