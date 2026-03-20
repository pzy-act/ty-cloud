package com.vip.tycloud.dto.inventory;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 采购单 - 更新请求 DTO。
 */
@Data
public class TyInvPurchaseOrderUpdateReqDTO {

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


