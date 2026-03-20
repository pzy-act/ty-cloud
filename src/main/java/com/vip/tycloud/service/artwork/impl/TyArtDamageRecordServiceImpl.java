package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtDamageRecord;
import com.vip.tycloud.repository.artwork.TyArtDamageRecordRepository;
import com.vip.tycloud.service.artwork.TyArtDamageRecordService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 破损记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtDamageRecordServiceImpl implements TyArtDamageRecordService {

    private final TyArtDamageRecordRepository tyArtDamageRecordRepository;

    @Override
    public TyArtDamageRecord getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtDamageRecordRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtDamageRecord> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtDamageRecord> page = new Page<>(current, size);
        IPage<TyArtDamageRecord> pageResult = tyArtDamageRecordRepository.page(
            page,
            Wrappers.<TyArtDamageRecord>lambdaQuery()
                .eq(TyArtDamageRecord::getIsDeleted, 0)
                .orderByDesc(TyArtDamageRecord::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyArtDamageRecord entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtDamageRecordRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtDamageRecord entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtDamageRecordRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtDamageRecordRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


