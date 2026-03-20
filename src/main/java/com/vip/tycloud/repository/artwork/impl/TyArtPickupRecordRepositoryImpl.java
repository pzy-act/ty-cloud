package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtPickupRecord;
import com.vip.tycloud.mapper.artwork.TyArtPickupRecordMapper;
import com.vip.tycloud.repository.artwork.TyArtPickupRecordRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 取件记录 - 仓储实现类。
 */
@Repository
public class TyArtPickupRecordRepositoryImpl extends BaseRepositoryImpl<TyArtPickupRecordMapper, TyArtPickupRecord> implements TyArtPickupRecordRepository {

    public TyArtPickupRecordRepositoryImpl(TyArtPickupRecordMapper baseMapper) {
        super(baseMapper);
    }
}


