package com.vip.tycloud.entity.student;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 学员监护关系 - 实体类。
 */
@Data
public class TyStuStudentGuardian {

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
     * 监护人ID
     */
    private Long guardianId;

    /**
     * 是否主联系人
     */
    private Integer isPrimaryContact;

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


