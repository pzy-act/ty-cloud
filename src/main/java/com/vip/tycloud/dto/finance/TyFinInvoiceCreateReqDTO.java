package com.vip.tycloud.dto.finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 发票管理 - 新增请求 DTO。
 */
@Data
public class TyFinInvoiceCreateReqDTO {

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


