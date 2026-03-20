package com.vip.tycloud.dto.schedule;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 资源预约 - 更新请求 DTO。
 */
@Data
public class TyResResourceBookingUpdateReqDTO {

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
     * 资源类型
     */
    private String resourceType;

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 预约日期
     */
    private LocalDate reserveDate;

    /**
     * 开始时间
     */
    private LocalTime startTime;

    /**
     * 结束时间
     */
    private LocalTime endTime;

    /**
     * 数量
     */
    private Integer qty;

    /**
     * 状态
     */
    private Integer status;
}


