package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * Student tag bind request DTO.
 */
@Data
public class TyStuStudentTagBindReqDTO {

    private Long campusId;

    private Long studentId;

    private Long tagId;
}
