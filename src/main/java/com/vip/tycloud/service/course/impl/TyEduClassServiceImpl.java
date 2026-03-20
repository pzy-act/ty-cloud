package com.vip.tycloud.service.course.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.course.TyEduClass;
import com.vip.tycloud.repository.course.TyEduClassRepository;
import com.vip.tycloud.service.course.TyEduClassService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课程与班级 功能模块 - 班级管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduClassServiceImpl implements TyEduClassService {

    private final TyEduClassRepository tyEduClassRepository;

    @Override
    public TyEduClass getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduClassRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduClass> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduClass> page = new Page<>(current, size);
        IPage<TyEduClass> pageResult = tyEduClassRepository.page(
            page,
            Wrappers.<TyEduClass>lambdaQuery()
                .eq(TyEduClass::getIsDeleted, 0)
                .orderByDesc(TyEduClass::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduClass entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduClassRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduClass entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduClassRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduClassRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


