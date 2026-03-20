package com.vip.tycloud.service.teacher.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherPayroll;
import com.vip.tycloud.repository.teacher.TyHrTeacherPayrollRepository;
import com.vip.tycloud.service.teacher.TyHrTeacherPayrollService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 教师与绩效 功能模块 - 工资核算 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyHrTeacherPayrollServiceImpl implements TyHrTeacherPayrollService {

    private final TyHrTeacherPayrollRepository tyHrTeacherPayrollRepository;

    @Override
    public TyHrTeacherPayroll getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyHrTeacherPayrollRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyHrTeacherPayroll> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyHrTeacherPayroll> page = new Page<>(current, size);
        IPage<TyHrTeacherPayroll> pageResult = tyHrTeacherPayrollRepository.page(
            page,
            Wrappers.<TyHrTeacherPayroll>lambdaQuery()
                .eq(TyHrTeacherPayroll::getIsDeleted, 0)
                .orderByDesc(TyHrTeacherPayroll::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyHrTeacherPayroll entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyHrTeacherPayrollRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyHrTeacherPayroll entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyHrTeacherPayrollRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyHrTeacherPayrollRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


