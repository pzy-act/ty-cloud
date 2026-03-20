package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtArtwork;
import com.vip.tycloud.repository.artwork.TyArtArtworkRepository;
import com.vip.tycloud.service.artwork.TyArtArtworkService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 作品建档 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtArtworkServiceImpl implements TyArtArtworkService {

    private final TyArtArtworkRepository tyArtArtworkRepository;

    @Override
    public TyArtArtwork getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtArtworkRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtArtwork> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtArtwork> page = new Page<>(current, size);
        IPage<TyArtArtwork> pageResult = tyArtArtworkRepository.page(
            page,
            Wrappers.<TyArtArtwork>lambdaQuery()
                .eq(TyArtArtwork::getIsDeleted, 0)
                .orderByDesc(TyArtArtwork::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyArtArtwork entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtArtworkRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtArtwork entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtArtworkRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtArtworkRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


