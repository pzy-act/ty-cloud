package com.vip.tycloud.dto.artwork;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 破损记录 - 响应 DTO。
 */
@Data
public class TyArtDamageRecordRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 作品ID
     */
    private Long artworkId;

    /**
     * 损坏时间
     */
    private LocalDateTime damageTime;

    /**
     * 损坏等级
     */
    private String damageLevel;

    /**
     * 损坏原因
     */
    private String damageReason;

    /**
     * 补偿方案
     */
    private String compensationPlan;

    /**
     * 处理人ID
     */
    private Long handledBy;

    /**
     * 处理时间
     */
    private LocalDateTime handledTime;

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


