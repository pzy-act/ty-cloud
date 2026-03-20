package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtFiringBatchItem;
import com.vip.tycloud.mapper.artwork.TyArtFiringBatchItemMapper;
import com.vip.tycloud.repository.artwork.TyArtFiringBatchItemRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 烧制明细 - 仓储实现类。
 */
@Repository
public class TyArtFiringBatchItemRepositoryImpl extends BaseRepositoryImpl<TyArtFiringBatchItemMapper, TyArtFiringBatchItem> implements TyArtFiringBatchItemRepository {

    public TyArtFiringBatchItemRepositoryImpl(TyArtFiringBatchItemMapper baseMapper) {
        super(baseMapper);
    }
}


