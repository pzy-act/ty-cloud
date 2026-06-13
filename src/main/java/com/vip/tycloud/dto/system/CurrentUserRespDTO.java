package com.vip.tycloud.dto.system;

import java.util.List;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 登录认证 - 当前用户响应 DTO。
 */
@Data
public class CurrentUserRespDTO {

    /**
     * 用户ID。
     */
    private Long userId;

    /**
     * 校区ID。
     */
    private Long campusId;

    /**
     * 用户名。
     */
    private String username;

    /**
     * 真实姓名。
     */
    private String realName;

    /**
     * 角色编码列表。
     */
    private List<String> roleCodes;

    /**
     * 权限标识列表。
     */
    private List<String> permissions;

    private List<CurrentUserMenuRespDTO> menus;
}
