package com.vip.tycloud.dto.finance;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 收费订单 - 更新请求 DTO。
 */
@Data
public class TyFinOrderUpdateReqDTO {

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
     * 订单号
     */
    private String orderNo;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

    /**
     * 截止时间
     */
    private LocalDateTime dueTime;

    /**
     * 状态
     */
    private Integer status;
}


