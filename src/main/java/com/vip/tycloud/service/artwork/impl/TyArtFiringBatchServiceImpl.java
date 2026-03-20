package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtFiringBatch;
import com.vip.tycloud.repository.artwork.TyArtFiringBatchRepository;
import com.vip.tycloud.service.artwork.TyArtFiringBatchService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 烧制批次 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtFiringBatchServiceImpl implements TyArtFiringBatchService {

    private final TyArtFiringBatchRepository tyArtFiringBatchRepository;

    @Override
    public TyArtFiringBatch getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtFiringBatchRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtFiringBatch> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtFiringBatch> page = new Page<>(current, size);
        IPage<TyArtFiringBatch> pageResult = tyArtFiringBatchRepository.page(
            page,
            Wrappers.<TyArtFiringBatch>lambdaQuery()
                .eq(TyArtFiringBatch::getIsDeleted, 0)
                .orderByDesc(TyArtFiringBatch::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyArtFiringBatch entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtFiringBatchRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtFiringBatch entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtFiringBatchRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtFiringBatchRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


