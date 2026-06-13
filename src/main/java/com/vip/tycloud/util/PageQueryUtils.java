package com.vip.tycloud.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;

/**
 * 分页查询条件工具。
 */
public final class PageQueryUtils {

    private PageQueryUtils() {
    }

    public static <T> QueryWrapper<T> baseQuery(String keyword, Integer status, String statusColumn, String... keywordColumns) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        if (StringUtils.hasText(statusColumn) && status != null) {
            queryWrapper.eq(statusColumn, status);
        }
        if (StringUtils.hasText(keyword) && keywordColumns != null && keywordColumns.length > 0) {
            String actualKeyword = keyword.trim();
            queryWrapper.and(wrapper -> {
                for (int index = 0; index < keywordColumns.length; index++) {
                    if (index == 0) {
                        wrapper.like(keywordColumns[index], actualKeyword);
                    } else {
                        wrapper.or().like(keywordColumns[index], actualKeyword);
                    }
                }
            });
        }
        queryWrapper.orderByDesc("id");
        return queryWrapper;
    }
}
