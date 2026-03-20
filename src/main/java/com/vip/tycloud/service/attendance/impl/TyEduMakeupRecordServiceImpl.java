package com.vip.tycloud.service.attendance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.attendance.TyEduMakeupRecord;
import com.vip.tycloud.repository.attendance.TyEduMakeupRecordRepository;
import com.vip.tycloud.service.attendance.TyEduMakeupRecordService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 考勤与消课 功能模块 - 补课管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduMakeupRecordServiceImpl implements TyEduMakeupRecordService {

    private final TyEduMakeupRecordRepository tyEduMakeupRecordRepository;

    @Override
    public TyEduMakeupRecord getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduMakeupRecordRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduMakeupRecord> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduMakeupRecord> page = new Page<>(current, size);
        IPage<TyEduMakeupRecord> pageResult = tyEduMakeupRecordRepository.page(
            page,
            Wrappers.<TyEduMakeupRecord>lambdaQuery()
                .eq(TyEduMakeupRecord::getIsDeleted, 0)
                .orderByDesc(TyEduMakeupRecord::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduMakeupRecord entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduMakeupRecordRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduMakeupRecord entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduMakeupRecordRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduMakeupRecordRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


