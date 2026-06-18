package com.vip.tycloud.repository.ai.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.ai.TyAiChatMessage;
import com.vip.tycloud.mapper.ai.TyAiChatMessageMapper;
import com.vip.tycloud.repository.ai.TyAiChatMessageRepository;
import org.springframework.stereotype.Repository;

/**
 * 智能问答 功能模块 - 消息 - 仓储实现类。
 */
@Repository
public class TyAiChatMessageRepositoryImpl extends BaseRepositoryImpl<TyAiChatMessageMapper, TyAiChatMessage> implements TyAiChatMessageRepository {

    public TyAiChatMessageRepositoryImpl(TyAiChatMessageMapper baseMapper) {
        super(baseMapper);
    }
}
