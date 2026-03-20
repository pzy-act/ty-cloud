package com.vip.tycloud.service.course.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.course.TyEduCourse;
import com.vip.tycloud.repository.course.TyEduCourseRepository;
import com.vip.tycloud.service.course.TyEduCourseService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 课程与班级 功能模块 - 课程管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduCourseServiceImpl implements TyEduCourseService {

    private final TyEduCourseRepository tyEduCourseRepository;

    @Override
    public TyEduCourse getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduCourseRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduCourse> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduCourse> page = new Page<>(current, size);
        IPage<TyEduCourse> pageResult = tyEduCourseRepository.page(
            page,
            Wrappers.<TyEduCourse>lambdaQuery()
                .eq(TyEduCourse::getIsDeleted, 0)
                .orderByDesc(TyEduCourse::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduCourse entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduCourseRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduCourse entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduCourseRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduCourseRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


