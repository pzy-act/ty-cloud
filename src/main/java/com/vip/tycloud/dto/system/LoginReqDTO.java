package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 登录认证 - 登录请求 DTO。
 */
@Data
public class LoginReqDTO {

    /**
     * 用户名。
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 登录密码。
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 是否开启 7 天免登录。
     */
    private Boolean rememberMe = false;
}
