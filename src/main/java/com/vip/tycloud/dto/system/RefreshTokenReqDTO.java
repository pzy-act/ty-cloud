package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 登录认证 - 刷新令牌请求 DTO。
 */
@Data
public class RefreshTokenReqDTO {

    /**
     * 刷新令牌。
     */
    @NotBlank(message = "刷新令牌不能为空")
    private String refreshToken;
}
