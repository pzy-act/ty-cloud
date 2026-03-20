package com.vip.tycloud.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 教室资源 - 更新请求 DTO。
 */
@Data
public class TyResClassroomUpdateReqDTO {

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
}


