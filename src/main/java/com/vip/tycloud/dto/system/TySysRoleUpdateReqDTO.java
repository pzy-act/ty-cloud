package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 角色管理 - 更新请求 DTO。
 */
@Data
public class TySysRoleUpdateReqDTO {

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
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}


