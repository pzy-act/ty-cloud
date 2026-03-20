package com.vip.tycloud.common.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 基础仓储实现。
 *
 * @param <M> Mapper 类型
 * @param <T> 实体类型
 */
@RequiredArgsConstructor
public abstract class BaseRepositoryImpl<M extends BaseMapper<T>, T> implements BaseRepository<T> {

    /**
     * 通用 Mapper。
     */
    protected final M baseMapper;

    @Override
    public T getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int save(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int updateById(T entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public int logicDeleteById(Long id, Long operatorId) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
            .set("is_deleted", 1)
            .set("updated_by", operatorId)
            .set("updated_time", LocalDateTime.now());
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<T> page(Page<T> page, Wrapper<T> wrapper) {
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public List<T> list(Wrapper<T> wrapper) {
        return baseMapper.selectList(wrapper);
    }
}
