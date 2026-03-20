package com.vip.tycloud.entity.inventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 库存流水 - 实体类。
 */
@Data
public class TyInvMaterialTxn {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 交易流水号
     */
    private String txnNo;

    /**
     * 物料ID
     */
    private Long materialId;

    /**
     * 交易类型
     */
    private String txnType;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 业务关联号
     */
    private String bizRefNo;

    /**
     * 交易时间
     */
    private LocalDateTime txnTime;

    /**
     * 操作人ID
     */
    private Long operatorId;

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


