package com.vip.tycloud.entity.artwork;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 烧制明细 - 实体类。
 */
@Data
public class TyArtFiringBatchItem {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 批次ID
     */
    private Long batchId;

    /**
     * 作品ID
     */
    private Long artworkId;

    /**
     * 位置号
     */
    private String positionNo;

    /**
     * 结果状态
     */
    private Integer resultStatus;

    /**
     * 结果备注
     */
    private String resultNote;

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


