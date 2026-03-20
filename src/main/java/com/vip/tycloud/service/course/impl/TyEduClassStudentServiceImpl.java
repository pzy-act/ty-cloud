package com.vip.tycloud.service.course.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.course.TyEduClassStudent;
import com.vip.tycloud.repository.course.TyEduClassStudentRepository;
import com.vip.tycloud.service.course.TyEduClassStudentService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课程与班级 功能模块 - 班级学员关联 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduClassStudentServiceImpl implements TyEduClassStudentService {

    private final TyEduClassStudentRepository tyEduClassStudentRepository;

    @Override
    public TyEduClassStudent getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduClassStudentRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduClassStudent> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduClassStudent> page = new Page<>(current, size);
        IPage<TyEduClassStudent> pageResult = tyEduClassStudentRepository.page(
            page,
            Wrappers.<TyEduClassStudent>lambdaQuery()
                .eq(TyEduClassStudent::getIsDeleted, 0)
                .orderByDesc(TyEduClassStudent::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduClassStudent entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduClassStudentRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduClassStudent entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduClassStudentRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduClassStudentRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


