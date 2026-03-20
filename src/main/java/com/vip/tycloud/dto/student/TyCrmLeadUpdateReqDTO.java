package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 线索管理 - 更新请求 DTO。
 */
@Data
public class TyCrmLeadUpdateReqDTO {

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


