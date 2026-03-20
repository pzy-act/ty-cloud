package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtFiringBatch;
import com.vip.tycloud.mapper.artwork.TyArtFiringBatchMapper;
import com.vip.tycloud.repository.artwork.TyArtFiringBatchRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 烧制批次 - 仓储实现类。
 */
@Repository
public class TyArtFiringBatchRepositoryImpl extends BaseRepositoryImpl<TyArtFiringBatchMapper, TyArtFiringBatch> implements TyArtFiringBatchRepository {

    public TyArtFiringBatchRepositoryImpl(TyArtFiringBatchMapper baseMapper) {
        super(baseMapper);
    }
}


