package com.vip.tycloud.dto.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 采购单 - 新增请求 DTO。
 */
@Data
public class TyInvPurchaseOrderCreateReqDTO {

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
}


