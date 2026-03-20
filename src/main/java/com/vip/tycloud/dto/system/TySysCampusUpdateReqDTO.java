package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 校区管理 - 更新请求 DTO。
 */
@Data
public class TySysCampusUpdateReqDTO {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空")
    private Long id;

    /**
     * 校区编码
     */
    private String campusCode;

    /**
     * 校区名称
     */
    private String campusName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态
     */
    private Integer status;
}


