package com.vip.tycloud.dto.schedule;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 设备资源 - 响应 DTO。
 */
@Data
public class TyResEquipmentRespDTO {

    /**
     * 主键ID
     */
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

    /**
     * 删除标记
     */
    private Integer isDeleted;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新人ID
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}


