package com.vip.tycloud.entity.attendance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 消课记录 - 实体类。
 */
@Data
public class TyEduLessonDeductLog {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 变更类型
     */
    private String changeType;

    /**
     * 扣减课时数
     */
    private Integer deductLessons;

    /**
     * 剩余课时数
     */
    private Integer remainingLessons;

    /**
     * 业务关联号
     */
    private String bizRefNo;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 变更时间
     */
    private LocalDateTime changeTime;

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


