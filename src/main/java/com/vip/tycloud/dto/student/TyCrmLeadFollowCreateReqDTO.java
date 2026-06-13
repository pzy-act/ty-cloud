package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Lead follow create request DTO.
 */
@Data
public class TyCrmLeadFollowCreateReqDTO {

    private Long campusId;

    @NotNull(message = "线索ID不能为空")
    private Long leadId;

    private String followType;

    private String content;

    private LocalDateTime followTime;

    private LocalDateTime nextFollowTime;

    private Long followerId;
}
