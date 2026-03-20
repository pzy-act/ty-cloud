package com.vip.tycloud.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 设备资源 - 更新请求 DTO。
 */
@Data
public class TyResEquipmentUpdateReqDTO {

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
     * 设备编码
     */
    private String equipmentCode;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备类型
     */
    private String equipmentType;

    /**
     * 总数量
     */
    private Integer quantityTotal;

    /**
     * 可用数量
     */
    private Integer quantityAvailable;

    /**
     * 状态
     */
    private Integer status;
}


