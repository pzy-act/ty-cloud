package com.vip.tycloud.repository.ai.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.ai.TyAiChatRequestLog;
import com.vip.tycloud.mapper.ai.TyAiChatRequestLogMapper;
import com.vip.tycloud.repository.ai.TyAiChatRequestLogRepository;
import org.springframework.stereotype.Repository;

/**
 * 智能问答 功能模块 - 调用日志 - 仓储实现类。
 */
@Repository
public class TyAiChatRequestLogRepositoryImpl extends BaseRepositoryImpl<TyAiChatRequestLogMapper, TyAiChatRequestLog> implements TyAiChatRequestLogRepository {

    public TyAiChatRequestLogRepositoryImpl(TyAiChatRequestLogMapper baseMapper) {
        super(baseMapper);
    }
}
