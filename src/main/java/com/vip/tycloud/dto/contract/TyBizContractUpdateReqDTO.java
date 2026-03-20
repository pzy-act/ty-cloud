package com.vip.tycloud.dto.contract;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 报名与合同 功能模块 - 报名合同 - 更新请求 DTO。
 */
@Data
public class TyBizContractUpdateReqDTO {

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
}


