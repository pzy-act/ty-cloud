package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * Student tag relation response DTO.
 */
@Data
public class TyStuStudentTagRespDTO {

    private Long id;

    private Long campusId;

    private Long studentId;

    private Long tagId;

    private String tagName;

    private String tagType;

    private String color;

    private Integer status;
}
