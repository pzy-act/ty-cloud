package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * Student guardian relation response DTO.
 */
@Data
public class TyStuStudentGuardianRespDTO {

    private Long id;

    private Long campusId;

    private Long studentId;

    private Long guardianId;

    private Integer isPrimaryContact;

    private String guardianName;

    private String relationType;

    private String mobile;

    private String wechat;

    private Integer status;
}
