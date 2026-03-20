package com.vip.tycloud.dto.finance;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 退款记录 - 更新请求 DTO。
 */
@Data
public class TyFinRefundUpdateReqDTO {

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
     * 退款单号
     */
    private String refundNo;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 支付ID
     */
    private Long paymentId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 原因
     */
    private String reason;

    /**
     * 申请人ID
     */
    private Long applyUserId;

    /**
     * 审批人ID
     */
    private Long approveUserId;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 状态
     */
    private Integer status;
}


