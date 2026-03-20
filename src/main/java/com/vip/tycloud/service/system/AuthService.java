package com.vip.tycloud.service.system;

import com.vip.tycloud.dto.system.CurrentUserRespDTO;
import com.vip.tycloud.dto.system.LoginReqDTO;
import com.vip.tycloud.dto.system.LoginRespDTO;

/**
 * 组织与权限 功能模块 - 登录认证 - 服务接口。
 */
public interface AuthService {

    /**
     * 登录认证。
     *
     * @param req 登录请求
     * @return 登录结果
     */
    LoginRespDTO login(LoginReqDTO req);

    /**
     * 刷新令牌。
     *
     * @param refreshToken 刷新令牌
     * @return 新令牌
     */
    LoginRespDTO refreshToken(String refreshToken);

    /**
     * 查询当前登录用户。
     *
     * @return 当前用户信息
     */
    CurrentUserRespDTO currentUser();

    /**
     * 退出登录。
     *
     * @return 是否成功
     */
    boolean logout();
}
