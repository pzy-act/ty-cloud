package com.vip.tycloud.dto.inventory;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 耗材与库存 功能模块 - 库存台账 - 更新请求 DTO。
 */
@Data
public class TyInvMaterialStockUpdateReqDTO {

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
     * 物料ID
     */
    private Long materialId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 在手数量
     */
    private BigDecimal qtyOnHand;

    /**
     * 锁定数量
     */
    private BigDecimal qtyLocked;

    /**
     * 最近入库时间
     */
    private LocalDateTime lastInTime;

    /**
     * 最近出库时间
     */
    private LocalDateTime lastOutTime;
}


