package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 菜单权限 - 更新请求 DTO。
 */
@Data
public class TySysMenuUpdateReqDTO {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空")
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 路由路径
     */
    private String routePath;

    /**
     * 组件
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 可见
     */
    private Integer visible;

    /**
     * 状态
     */
    private Integer status;
}


