package com.vip.tycloud.repository.ai.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.ai.TyAiUsageRecord;
import com.vip.tycloud.mapper.ai.TyAiUsageRecordMapper;
import com.vip.tycloud.repository.ai.TyAiUsageRecordRepository;
import org.springframework.stereotype.Repository;

/**
 * 智能问答 功能模块 - 用量流水 - 仓储实现类。
 */
@Repository
public class TyAiUsageRecordRepositoryImpl extends BaseRepositoryImpl<TyAiUsageRecordMapper, TyAiUsageRecord> implements TyAiUsageRecordRepository {

    public TyAiUsageRecordRepositoryImpl(TyAiUsageRecordMapper baseMapper) {
        super(baseMapper);
    }
}
