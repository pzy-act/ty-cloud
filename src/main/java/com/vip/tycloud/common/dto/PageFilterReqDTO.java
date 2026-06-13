package com.vip.tycloud.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页筛选请求。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageFilterReqDTO extends PageQueryReqDTO {

    /**
     * 关键字。
     */
    private String keyword;

    /**
     * 状态。
     */
    private Integer status;
}
