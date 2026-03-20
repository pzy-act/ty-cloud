package com.vip.tycloud.common.dto;

import java.util.Collections;
import java.util.List;
import lombok.Data;

/**
 * 分页结果。
 *
 * @param <T> 数据类型
 */
@Data
public class PageResultDTO<T> {

    /**
     * 总记录数。
     */
    private Long total;

    /**
     * 当前页记录。
     */
    private List<T> records;

    /**
     * 组装分页结果。
     *
     * @param total 总数
     * @param records 记录
     * @param <T> 数据类型
     * @return 分页结果
     */
    public static <T> PageResultDTO<T> of(Long total, List<T> records) {
        PageResultDTO<T> result = new PageResultDTO<>();
        result.setTotal(total == null ? 0L : total);
        result.setRecords(records == null ? Collections.emptyList() : records);
        return result;
    }
}
