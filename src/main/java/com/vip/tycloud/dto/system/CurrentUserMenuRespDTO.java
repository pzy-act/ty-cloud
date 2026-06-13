package com.vip.tycloud.dto.system;

import java.util.List;
import lombok.Data;

/**
 * 当前用户授权菜单响应 DTO。
 */
@Data
public class CurrentUserMenuRespDTO {

    private Long id;

    private Long parentId;

    private String menuName;

    private Integer menuType;

    private String routePath;

    private String component;

    private String perms;

    private String icon;

    private Integer sortNo;

    private List<CurrentUserMenuRespDTO> children;
}
