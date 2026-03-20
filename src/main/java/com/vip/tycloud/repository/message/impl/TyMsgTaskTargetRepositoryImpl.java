package com.vip.tycloud.repository.message.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.message.TyMsgTaskTarget;
import com.vip.tycloud.mapper.message.TyMsgTaskTargetMapper;
import com.vip.tycloud.repository.message.TyMsgTaskTargetRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知与互动 功能模块 - 消息任务对象 - 仓储实现类。
 */
@Repository
public class TyMsgTaskTargetRepositoryImpl extends BaseRepositoryImpl<TyMsgTaskTargetMapper, TyMsgTaskTarget> implements TyMsgTaskTargetRepository {

    public TyMsgTaskTargetRepositoryImpl(TyMsgTaskTargetMapper baseMapper) {
        super(baseMapper);
    }
}


