package com.vip.tycloud.dto.student;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Lead follow response DTO.
 */
@Data
public class TyCrmLeadFollowRespDTO {

    private Long id;

    private Long campusId;

    private Long leadId;

    private String followType;

    private String content;

    private LocalDateTime followTime;

    private LocalDateTime nextFollowTime;

    private Long followerId;

    private Integer isDeleted;

    private Long createdBy;

    private LocalDateTime createdTime;

    private Long updatedBy;

    private LocalDateTime updatedTime;
}
