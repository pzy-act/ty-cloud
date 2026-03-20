package com.vip.tycloud.dto.inventory;

import lombok.Data;

/**
 * 耗材与库存 功能模块 - 供应商管理 - 新增请求 DTO。
 */
@Data
public class TyInvSupplierCreateReqDTO {

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
}


