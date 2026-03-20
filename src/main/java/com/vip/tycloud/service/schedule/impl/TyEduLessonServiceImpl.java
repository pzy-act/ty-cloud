package com.vip.tycloud.service.schedule.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.schedule.TyEduLesson;
import com.vip.tycloud.repository.schedule.TyEduLessonRepository;
import com.vip.tycloud.service.schedule.TyEduLessonService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 排课与资源 功能模块 - 课表管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduLessonServiceImpl implements TyEduLessonService {

    private final TyEduLessonRepository tyEduLessonRepository;

    @Override
    public TyEduLesson getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduLessonRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduLesson> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduLesson> page = new Page<>(current, size);
        IPage<TyEduLesson> pageResult = tyEduLessonRepository.page(
            page,
            Wrappers.<TyEduLesson>lambdaQuery()
                .eq(TyEduLesson::getIsDeleted, 0)
                .orderByDesc(TyEduLesson::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduLesson entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduLessonRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduLesson entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduLessonRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduLessonRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


