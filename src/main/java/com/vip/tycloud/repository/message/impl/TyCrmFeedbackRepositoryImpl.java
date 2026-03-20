package com.vip.tycloud.repository.message.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.message.TyCrmFeedback;
import com.vip.tycloud.mapper.message.TyCrmFeedbackMapper;
import com.vip.tycloud.repository.message.TyCrmFeedbackRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知与互动 功能模块 - 家长反馈 - 仓储实现类。
 */
@Repository
public class TyCrmFeedbackRepositoryImpl extends BaseRepositoryImpl<TyCrmFeedbackMapper, TyCrmFeedback> implements TyCrmFeedbackRepository {

    public TyCrmFeedbackRepositoryImpl(TyCrmFeedbackMapper baseMapper) {
        super(baseMapper);
    }
}


