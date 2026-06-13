package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

/**
 * 用户分配角色请求 DTO。
 */
@Data
public class TySysUserRoleAssignReqDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private List<Long> roleIds;

    private Long operatorId;
}
