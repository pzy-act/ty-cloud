package com.vip.tycloud.dto.contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 报名与合同 功能模块 - 报名合同 - 响应 DTO。
 */
@Data
public class TyBizContractRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 监护人ID
     */
    private Long guardianId;

    /**
     * 课包ID
     */
    private Long packageId;

    /**
     * 签约日期
     */
    private LocalDate signDate;

    /**
     * 生效日期
     */
    private LocalDate effectiveDate;

    /**
     * 到期日期
     */
    private LocalDate expireDate;

    /**
     * 原始金额
     */
    private BigDecimal originalAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;

    /**
     * 已收金额
     */
    private BigDecimal paidAmount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 销售ID
     */
    private Long salespersonId;

    /**
     * 备注
     */
    private String remark;

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


