package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtDamageRecord;
import com.vip.tycloud.mapper.artwork.TyArtDamageRecordMapper;
import com.vip.tycloud.repository.artwork.TyArtDamageRecordRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 破损记录 - 仓储实现类。
 */
@Repository
public class TyArtDamageRecordRepositoryImpl extends BaseRepositoryImpl<TyArtDamageRecordMapper, TyArtDamageRecord> implements TyArtDamageRecordRepository {

    public TyArtDamageRecordRepositoryImpl(TyArtDamageRecordMapper baseMapper) {
        super(baseMapper);
    }
}


