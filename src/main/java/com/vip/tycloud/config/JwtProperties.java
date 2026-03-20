package com.vip.tycloud.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * 认证授权 - JWT 配置属性。
 */
@Data
@Validated
@ConfigurationProperties(prefix = "ty.security.jwt")
public class JwtProperties {

    /**
     * JWT 签名密钥。
     */
    @NotBlank
    private String secret;

    /**
     * 访问令牌有效期（秒）。
     */
    @Min(60)
    private long accessTokenExpireSeconds = 7200;

    /**
     * 普通刷新令牌有效期（秒）。
     */
    @Min(300)
    private long refreshTokenExpireSeconds = 86400;

    /**
     * 7 天免登录刷新令牌有效期（秒）。
     */
    @Min(300)
    private long rememberMeRefreshTokenExpireSeconds = 604800;
}
