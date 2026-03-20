package com.vip.tycloud.dto.inventory;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 耗材档案 - 新增请求 DTO。
 */
@Data
public class TyInvMaterialCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格
     */
    private String spec;

    /**
     * 安全库存
     */
    private BigDecimal safeStock;

    /**
     * 状态
     */
    private Integer status;
}


