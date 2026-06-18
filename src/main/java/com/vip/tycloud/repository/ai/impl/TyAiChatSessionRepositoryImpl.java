package com.vip.tycloud.repository.ai.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.ai.TyAiChatSession;
import com.vip.tycloud.mapper.ai.TyAiChatSessionMapper;
import com.vip.tycloud.repository.ai.TyAiChatSessionRepository;
import org.springframework.stereotype.Repository;

/**
 * 智能问答 功能模块 - 会话 - 仓储实现类。
 */
@Repository
public class TyAiChatSessionRepositoryImpl extends BaseRepositoryImpl<TyAiChatSessionMapper, TyAiChatSession> implements TyAiChatSessionRepository {

    public TyAiChatSessionRepositoryImpl(TyAiChatSessionMapper baseMapper) {
        super(baseMapper);
    }
}
