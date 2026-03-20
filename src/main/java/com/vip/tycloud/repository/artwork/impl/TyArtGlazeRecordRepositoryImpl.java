package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtGlazeRecord;
import com.vip.tycloud.mapper.artwork.TyArtGlazeRecordMapper;
import com.vip.tycloud.repository.artwork.TyArtGlazeRecordRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 上釉记录 - 仓储实现类。
 */
@Repository
public class TyArtGlazeRecordRepositoryImpl extends BaseRepositoryImpl<TyArtGlazeRecordMapper, TyArtGlazeRecord> implements TyArtGlazeRecordRepository {

    public TyArtGlazeRecordRepositoryImpl(TyArtGlazeRecordMapper baseMapper) {
        super(baseMapper);
    }
}


