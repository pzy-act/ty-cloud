package com.vip.tycloud.dto.course;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 课包管理 - 响应 DTO。
 */
@Data
public class TyEduCoursePackageRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 课包编码
     */
    private String packageCode;

    /**
     * 课包名称
     */
    private String packageName;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 总课时数
     */
    private Integer totalLessons;

    /**
     * 赠送课时数
     */
    private Integer giftLessons;

    /**
     * 有效天数
     */
    private Integer validDays;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

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


