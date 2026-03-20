package com.vip.tycloud.dto.schedule;

import lombok.Data;

/**
 * 排课与资源 功能模块 - 教室资源 - 新增请求 DTO。
 */
@Data
public class TyResClassroomCreateReqDTO {

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


