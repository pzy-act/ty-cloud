package com.vip.tycloud.entity.finance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 收费与订单 功能模块 - 发票管理 - 实体类。
 */
@Data
public class TyFinInvoice {

    /**
     * 主键ID
     */
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


