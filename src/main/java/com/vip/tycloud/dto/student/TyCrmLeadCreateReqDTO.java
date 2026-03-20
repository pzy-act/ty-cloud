package com.vip.tycloud.dto.student;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 线索管理 - 新增请求 DTO。
 */
@Data
public class TyCrmLeadCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 线索名称
     */
    private String leadName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 来源渠道ID
     */
    private Long sourceChannelId;

    /**
     * 意向等级
     */
    private Integer intentLevel;

    /**
     * 意向课程
     */
    private String interestedCourse;

    /**
     * 负责人用户ID
     */
    private Long ownerUserId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextFollowTime;
}


