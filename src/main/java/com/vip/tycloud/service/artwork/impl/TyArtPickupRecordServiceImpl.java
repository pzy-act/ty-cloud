package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtPickupRecord;
import com.vip.tycloud.repository.artwork.TyArtPickupRecordRepository;
import com.vip.tycloud.service.artwork.TyArtPickupRecordService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 取件记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtPickupRecordServiceImpl implements TyArtPickupRecordService {

    private final TyArtPickupRecordRepository tyArtPickupRecordRepository;

    @Override
    public TyArtPickupRecord getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtPickupRecordRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtPickupRecord> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtPickupRecord> page = new Page<>(current, size);
        IPage<TyArtPickupRecord> pageResult = tyArtPickupRecordRepository.page(
            page,
            Wrappers.<TyArtPickupRecord>lambdaQuery()
                .eq(TyArtPickupRecord::getIsDeleted, 0)
                .orderByDesc(TyArtPickupRecord::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyArtPickupRecord entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtPickupRecordRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtPickupRecord entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtPickupRecordRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtPickupRecordRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


