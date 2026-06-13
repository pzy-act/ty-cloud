package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

/**
 * 角色菜单授权请求 DTO。
 */
@Data
public class TySysRoleMenuAssignReqDTO {

    /**
     * 角色 ID。
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    /**
     * 菜单 ID 列表。
     */
    private List<Long> menuIds;

    /**
     * 操作人 ID。
     */
    private Long operatorId;
}
