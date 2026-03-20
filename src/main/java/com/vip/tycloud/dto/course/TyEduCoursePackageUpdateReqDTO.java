package com.vip.tycloud.dto.course;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 课包管理 - 更新请求 DTO。
 */
@Data
public class TyEduCoursePackageUpdateReqDTO {

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
}


