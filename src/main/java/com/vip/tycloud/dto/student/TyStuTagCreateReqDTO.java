package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * 学员管理 功能模块 - 学员标签 - 新增请求 DTO。
 */
@Data
public class TyStuTagCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签类型
     */
    private String tagType;

    /**
     * 颜色
     */
    private String color;

    /**
     * 状态
     */
    private Integer status;
}


