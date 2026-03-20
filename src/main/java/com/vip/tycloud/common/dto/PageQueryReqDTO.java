package com.vip.tycloud.common.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 分页查询请求。
 */
@Data
public class PageQueryReqDTO {

    /**
     * 页码，从 1 开始。
     */
    @Min(1)
    private Integer pageNumber = 1;

    /**
     * 每页条数。
     */
    @Min(1)
    private Integer pageSize = 10;
}
