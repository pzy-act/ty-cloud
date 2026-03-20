package com.vip.tycloud.dto.artwork;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 烧制批次 - 更新请求 DTO。
 */
@Data
public class TyArtFiringBatchUpdateReqDTO {

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
}


