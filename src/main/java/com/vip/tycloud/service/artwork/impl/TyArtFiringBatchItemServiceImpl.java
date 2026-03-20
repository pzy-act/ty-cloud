package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtFiringBatchItem;
import com.vip.tycloud.repository.artwork.TyArtFiringBatchItemRepository;
import com.vip.tycloud.service.artwork.TyArtFiringBatchItemService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 烧制明细 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtFiringBatchItemServiceImpl implements TyArtFiringBatchItemService {

    private final TyArtFiringBatchItemRepository tyArtFiringBatchItemRepository;

    @Override
    public TyArtFiringBatchItem getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtFiringBatchItemRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtFiringBatchItem> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtFiringBatchItem> page = new Page<>(current, size);
        IPage<TyArtFiringBatchItem> pageResult = tyArtFiringBatchItemRepository.page(
            page,
            Wrappers.<TyArtFiringBatchItem>lambdaQuery()
                .eq(TyArtFiringBatchItem::getIsDeleted, 0)
                .orderByDesc(TyArtFiringBatchItem::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyArtFiringBatchItem entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtFiringBatchItemRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtFiringBatchItem entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtFiringBatchItemRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtFiringBatchItemRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


