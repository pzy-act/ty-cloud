package com.vip.tycloud.dto.student;

import com.vip.tycloud.common.dto.PageQueryReqDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Student page request DTO.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TyStuStudentPageReqDTO extends PageQueryReqDTO {

    private String keyword;

    private Integer status;

    private Long tagId;
}
