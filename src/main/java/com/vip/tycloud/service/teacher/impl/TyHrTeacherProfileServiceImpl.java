package com.vip.tycloud.service.teacher.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherProfile;
import com.vip.tycloud.repository.teacher.TyHrTeacherProfileRepository;
import com.vip.tycloud.service.teacher.TyHrTeacherProfileService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 教师与绩效 功能模块 - 教师档案 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyHrTeacherProfileServiceImpl implements TyHrTeacherProfileService {

    private final TyHrTeacherProfileRepository tyHrTeacherProfileRepository;

    @Override
    public TyHrTeacherProfile getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyHrTeacherProfileRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyHrTeacherProfile> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyHrTeacherProfile> page = new Page<>(current, size);
        IPage<TyHrTeacherProfile> pageResult = tyHrTeacherProfileRepository.page(
            page,
            Wrappers.<TyHrTeacherProfile>lambdaQuery()
                .eq(TyHrTeacherProfile::getIsDeleted, 0)
                .orderByDesc(TyHrTeacherProfile::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyHrTeacherProfile entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyHrTeacherProfileRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyHrTeacherProfile entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyHrTeacherProfileRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyHrTeacherProfileRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


