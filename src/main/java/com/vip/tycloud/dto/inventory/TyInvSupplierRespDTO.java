package com.vip.tycloud.dto.inventory;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 供应商管理 - 响应 DTO。
 */
@Data
public class TyInvSupplierRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 地址
     */
    private String address;

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


