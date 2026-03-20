package com.vip.tycloud.common.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * 基础仓储接口。
 *
 * @param <T> 实体类型
 */
public interface BaseRepository<T> {

    /**
     * 根据主键查询。
     *
     * @param id 主键 ID
     * @return 实体对象
     */
    T getById(Long id);

    /**
     * 新增记录。
     *
     * @param entity 实体
     * @return 影响行数
     */
    int save(T entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体
     * @return 影响行数
     */
    int updateById(T entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键 ID
     * @param operatorId 操作人 ID
     * @return 影响行数
     */
    int logicDeleteById(Long id, Long operatorId);

    /**
     * 分页查询。
     *
     * @param page 分页参数
     * @param wrapper 条件
     * @return 分页结果
     */
    IPage<T> page(Page<T> page, Wrapper<T> wrapper);

    /**
     * 条件列表查询。
     *
     * @param wrapper 条件
     * @return 结果列表
     */
    List<T> list(Wrapper<T> wrapper);
}
