package com.vip.tycloud.dto.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 学员档案 - 新增请求 DTO。
 */
@Data
public class TyStuStudentCreateReqDTO {

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


