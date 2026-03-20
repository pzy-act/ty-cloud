package com.vip.tycloud.service.course.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.course.TyEduCoursePackage;
import com.vip.tycloud.repository.course.TyEduCoursePackageRepository;
import com.vip.tycloud.service.course.TyEduCoursePackageService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课程与班级 功能模块 - 课包管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduCoursePackageServiceImpl implements TyEduCoursePackageService {

    private final TyEduCoursePackageRepository tyEduCoursePackageRepository;

    @Override
    public TyEduCoursePackage getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduCoursePackageRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduCoursePackage> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduCoursePackage> page = new Page<>(current, size);
        IPage<TyEduCoursePackage> pageResult = tyEduCoursePackageRepository.page(
            page,
            Wrappers.<TyEduCoursePackage>lambdaQuery()
                .eq(TyEduCoursePackage::getIsDeleted, 0)
                .orderByDesc(TyEduCoursePackage::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduCoursePackage entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduCoursePackageRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduCoursePackage entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduCoursePackageRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduCoursePackageRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


