package com.vip.tycloud.entity.artwork;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 烧制批次 - 实体类。
 */
@Data
public class TyArtFiringBatch {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 烧制批次号
     */
    private String batchNo;

    /**
     * 窑炉名称
     */
    private String kilnName;

    /**
     * 烧制类型
     */
    private String fireType;

    /**
     * 计划时间
     */
    private LocalDateTime scheduleTime;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 目标温度
     */
    private BigDecimal targetTemp;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 操作人ID
     */
    private Long operatorId;

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


