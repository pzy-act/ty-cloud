package com.vip.tycloud.service.schedule.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.schedule.TyResClassroom;
import com.vip.tycloud.repository.schedule.TyResClassroomRepository;
import com.vip.tycloud.service.schedule.TyResClassroomService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 排课与资源 功能模块 - 教室资源 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyResClassroomServiceImpl implements TyResClassroomService {

    private final TyResClassroomRepository tyResClassroomRepository;

    @Override
    public TyResClassroom getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyResClassroomRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyResClassroom> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyResClassroom> page = new Page<>(current, size);
        IPage<TyResClassroom> pageResult = tyResClassroomRepository.page(
            page,
            Wrappers.<TyResClassroom>lambdaQuery()
                .eq(TyResClassroom::getIsDeleted, 0)
                .orderByDesc(TyResClassroom::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyResClassroom entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyResClassroomRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyResClassroom entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyResClassroomRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyResClassroomRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


