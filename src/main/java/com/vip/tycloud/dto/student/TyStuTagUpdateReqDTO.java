package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 学员管理 功能模块 - 学员标签 - 更新请求 DTO。
 */
@Data
public class TyStuTagUpdateReqDTO {

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


