package com.vip.tycloud.dto.inventory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 库存台账 - 响应 DTO。
 */
@Data
public class TyInvMaterialStockRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 物料ID
     */
    private Long materialId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 在手数量
     */
    private BigDecimal qtyOnHand;

    /**
     * 锁定数量
     */
    private BigDecimal qtyLocked;

    /**
     * 最近入库时间
     */
    private LocalDateTime lastInTime;

    /**
     * 最近出库时间
     */
    private LocalDateTime lastOutTime;

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


