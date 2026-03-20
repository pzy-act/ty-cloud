package com.vip.tycloud.entity.system;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 操作日志 - 实体类。
 */
@Data
public class TySysOperationLog {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 模块
     */
    private String module;

    /**
     * 操作
     */
    private String action;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * IP
     */
    private String ip;

    /**
     * 结果编码
     */
    private String resultCode;

    /**
     * 耗时（毫秒）
     */
    private Long costMs;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

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


