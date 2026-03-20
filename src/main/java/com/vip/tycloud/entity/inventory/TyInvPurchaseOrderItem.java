package com.vip.tycloud.entity.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 采购明细 - 实体类。
 */
@Data
public class TyInvPurchaseOrderItem {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 采购单ID
     */
    private Long poId;

    /**
     * 物料ID
     */
    private Long materialId;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 已收数量
     */
    private BigDecimal receivedQty;

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


