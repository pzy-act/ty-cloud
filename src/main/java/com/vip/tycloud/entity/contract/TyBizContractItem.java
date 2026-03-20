package com.vip.tycloud.entity.contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 报名与合同 功能模块 - 合同明细 - 实体类。
 */
@Data
public class TyBizContractItem {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课包课时数
     */
    private Integer packageLessons;

    /**
     * 赠送课时数
     */
    private Integer giftLessons;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 小计金额
     */
    private BigDecimal subtotalAmount;

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


