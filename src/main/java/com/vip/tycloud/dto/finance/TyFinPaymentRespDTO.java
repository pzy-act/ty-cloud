package com.vip.tycloud.dto.finance;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 收款记录 - 响应 DTO。
 */
@Data
public class TyFinPaymentRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 支付单号
     */
    private String paymentNo;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 支付方式
     */
    private String payMethod;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 交易流水号
     */
    private String transactionNo;

    /**
     * 收银员ID
     */
    private Long cashierId;

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


