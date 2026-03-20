package com.vip.tycloud.dto.course;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 课包管理 - 新增请求 DTO。
 */
@Data
public class TyEduCoursePackageCreateReqDTO {

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


