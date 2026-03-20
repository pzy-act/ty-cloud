package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 学员档案 - 更新请求 DTO。
 */
@Data
public class TyStuStudentUpdateReqDTO {

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
     * 学员编号
     */
    private String studentNo;

    /**
     * 学员名称
     */
    private String studentName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 学校
     */
    private String school;

    /**
     * 等级
     */
    private String level;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 首次报名时间
     */
    private LocalDateTime firstEnrollTime;
}


