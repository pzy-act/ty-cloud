package com.vip.tycloud.dto.schedule;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 教室资源 - 响应 DTO。
 */
@Data
public class TyResClassroomRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 教室编码
     */
    private String roomCode;

    /**
     * 教室名称
     */
    private String roomName;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 位置
     */
    private String location;

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


