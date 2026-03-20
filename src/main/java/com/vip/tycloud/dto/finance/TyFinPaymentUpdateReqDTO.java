package com.vip.tycloud.dto.finance;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 收款记录 - 更新请求 DTO。
 */
@Data
public class TyFinPaymentUpdateReqDTO {

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
}


