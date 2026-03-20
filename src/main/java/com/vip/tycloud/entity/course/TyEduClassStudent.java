package com.vip.tycloud.entity.course;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 班级学员关联 - 实体类。
 */
@Data
public class TyEduClassStudent {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 加入日期
     */
    private LocalDate joinDate;

    /**
     * 请假日期
     */
    private LocalDate leaveDate;

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


