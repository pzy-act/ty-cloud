package com.vip.tycloud.repository.system;

import com.vip.tycloud.common.repository.BaseRepository;
import com.vip.tycloud.entity.system.TySysUser;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 组织与权限 功能模块 - 用户管理 - 仓储接口。
 */
public interface TySysUserRepository extends BaseRepository<TySysUser> {

    /**
     * 按用户名查询用户。
     *
     * @param username 用户名
     * @return 用户信息
     */
    TySysUser findByUsername(String username);

    /**
     * 查询用户角色编码列表。
     *
     * @param userId 用户ID
     * @return 角色编码列表
     */
    List<String> listRoleCodesByUserId(Long userId);

    /**
     * 查询用户权限标识列表。
     *
     * @param userId 用户ID
     * @return 权限标识列表
     */
    List<String> listPermsByUserId(Long userId);

    /**
     * 更新最近登录时间。
     *
     * @param userId 用户ID
     * @param lastLoginTime 最近登录时间
     * @return 是否成功
     */
    boolean updateLastLoginTime(Long userId, LocalDateTime lastLoginTime);
}
