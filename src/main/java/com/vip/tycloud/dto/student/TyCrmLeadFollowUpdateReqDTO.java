package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Lead follow update request DTO.
 */
@Data
public class TyCrmLeadFollowUpdateReqDTO {

    @NotNull(message = "主键ID不能为空")
    private Long id;

    private Long campusId;

    @NotNull(message = "线索ID不能为空")
    private Long leadId;

    private String followType;

    private String content;

    private LocalDateTime followTime;

    private LocalDateTime nextFollowTime;

    private Long followerId;
}
