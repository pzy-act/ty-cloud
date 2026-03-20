package com.vip.tycloud.dto.finance;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 发票管理 - 更新请求 DTO。
 */
@Data
public class TyFinInvoiceUpdateReqDTO {

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
     * 发票号
     */
    private String invoiceNo;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 抬头
     */
    private String title;

    /**
     * 税号
     */
    private String taxNo;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 开票日期
     */
    private LocalDate issueDate;

    /**
     * 状态
     */
    private Integer status;
}


