package com.vip.tycloud.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.entity.system.TySysUser;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 组织与权限 功能模块 - 用户管理 - Mapper 接口。
 */
@Mapper
public interface TySysUserMapper extends BaseMapper<TySysUser> {

    /**
     * 按用户名查询用户。
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT id, campus_id, username, password_hash, real_name, mobile, email, job_no, status, "
        + "last_login_time, is_deleted, created_by, created_time, updated_by, updated_time "
        + "FROM ty_sys_user WHERE username = #{username} AND is_deleted = 0")
    TySysUser selectByUsername(@Param("username") String username);

    /**
     * 查询用户角色编码列表。
     *
     * @param userId 用户ID
     * @return 角色编码列表
     */
    @Select("SELECT DISTINCT r.role_code "
        + "FROM ty_sys_user_role ur "
        + "JOIN ty_sys_role r ON ur.role_id = r.id "
        + "WHERE ur.user_id = #{userId} "
        + "AND ur.is_deleted = 0 "
        + "AND r.is_deleted = 0 "
        + "AND r.status = 1")
    List<String> selectRoleCodesByUserId(@Param("userId") Long userId);

    /**
     * 查询用户权限标识列表。
     *
     * @param userId 用户ID
     * @return 权限标识列表
     */
    @Select("SELECT DISTINCT m.perms "
        + "FROM ty_sys_user_role ur "
        + "JOIN ty_sys_role_menu rm ON ur.role_id = rm.role_id "
        + "JOIN ty_sys_menu m ON rm.menu_id = m.id "
        + "WHERE ur.user_id = #{userId} "
        + "AND ur.is_deleted = 0 "
        + "AND rm.is_deleted = 0 "
        + "AND m.is_deleted = 0 "
        + "AND m.status = 1 "
        + "AND m.perms IS NOT NULL "
        + "AND m.perms <> ''")
    List<String> selectPermsByUserId(@Param("userId") Long userId);

    /**
     * 查询用户授权菜单列表。
     *
     * @param userId 用户ID
     * @return 授权菜单列表
     */
    @Select("SELECT DISTINCT m.id, m.campus_id, m.parent_id, m.menu_name, m.menu_type, "
        + "m.route_path, m.component, m.perms, m.icon, m.sort_no, m.visible, m.status, "
        + "m.is_deleted, m.created_by, m.created_time, m.updated_by, m.updated_time "
        + "FROM ty_sys_user_role ur "
        + "JOIN ty_sys_role_menu rm ON ur.role_id = rm.role_id "
        + "JOIN ty_sys_menu m ON rm.menu_id = m.id "
        + "WHERE ur.user_id = #{userId} "
        + "AND ur.is_deleted = 0 "
        + "AND rm.is_deleted = 0 "
        + "AND m.is_deleted = 0 "
        + "AND m.status = 1 "
        + "AND m.visible = 1 "
        + "ORDER BY m.parent_id ASC, m.sort_no ASC, m.id ASC")
    List<TySysMenu> selectMenusByUserId(@Param("userId") Long userId);

    /**
     * 更新最近登录时间。
     *
     * @param userId 用户ID
     * @param lastLoginTime 最近登录时间
     * @return 影响行数
     */
    @Update("UPDATE ty_sys_user SET last_login_time = #{lastLoginTime}, updated_time = #{lastLoginTime} "
        + "WHERE id = #{userId} AND is_deleted = 0")
    int updateLastLoginTime(@Param("userId") Long userId, @Param("lastLoginTime") LocalDateTime lastLoginTime);
}
