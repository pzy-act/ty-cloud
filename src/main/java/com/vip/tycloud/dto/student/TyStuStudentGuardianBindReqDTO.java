package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * Student guardian bind request DTO.
 */
@Data
public class TyStuStudentGuardianBindReqDTO {

    private Long campusId;

    private Long studentId;

    private Long guardianId;

    private Integer isPrimaryContact;
}
