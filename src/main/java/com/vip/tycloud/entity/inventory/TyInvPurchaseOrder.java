package com.vip.tycloud.entity.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 采购单 - 实体类。
 */
@Data
public class TyInvPurchaseOrder {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 采购单号
     */
    private String poNo;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 下单日期
     */
    private LocalDate orderDate;

    /**
     * 预计到货日期
     */
    private LocalDate expectedArrivalDate;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 采购员ID
     */
    private Long buyerId;

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


