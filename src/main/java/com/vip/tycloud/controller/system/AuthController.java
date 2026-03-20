package com.vip.tycloud.controller.system;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.dto.system.CurrentUserRespDTO;
import com.vip.tycloud.dto.system.LoginReqDTO;
import com.vip.tycloud.dto.system.LoginRespDTO;
import com.vip.tycloud.dto.system.RefreshTokenReqDTO;
import com.vip.tycloud.service.system.AuthService;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织与权限 功能模块 - 登录认证 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 账号登录。
     *
     * @param req 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public ApiResponse<LoginRespDTO> login(@Valid @RequestBody LoginReqDTO req) {
        LoginRespDTO respDTO = authService.login(req);
        if (Objects.isNull(respDTO)) {
            return ApiResponse.fail("用户名或密码错误");
        }
        return ApiResponse.success(respDTO);
    }

    /**
     * 刷新令牌。
     *
     * @param req 刷新请求
     * @return 新令牌
     */
    @PostMapping("/refresh")
    public ApiResponse<LoginRespDTO> refresh(@Valid @RequestBody RefreshTokenReqDTO req) {
        LoginRespDTO respDTO = authService.refreshToken(req.getRefreshToken());
        if (Objects.isNull(respDTO)) {
            return ApiResponse.fail("刷新令牌无效或已过期");
        }
        return ApiResponse.success(respDTO);
    }

    /**
     * 查询当前登录用户。
     *
     * @return 当前用户
     */
    @GetMapping("/me")
    public ApiResponse<CurrentUserRespDTO> me() {
        CurrentUserRespDTO respDTO = authService.currentUser();
        if (Objects.isNull(respDTO)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(respDTO);
    }

    /**
     * 退出登录。
     *
     * @return 是否成功
     */
    @PostMapping("/logout")
    public ApiResponse<Boolean> logout() {
        return ApiResponse.success(authService.logout());
    }
}
