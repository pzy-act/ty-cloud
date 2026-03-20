package com.vip.tycloud.repository.message.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.message.TyMsgTask;
import com.vip.tycloud.mapper.message.TyMsgTaskMapper;
import com.vip.tycloud.repository.message.TyMsgTaskRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知与互动 功能模块 - 消息任务 - 仓储实现类。
 */
@Repository
public class TyMsgTaskRepositoryImpl extends BaseRepositoryImpl<TyMsgTaskMapper, TyMsgTask> implements TyMsgTaskRepository {

    public TyMsgTaskRepositoryImpl(TyMsgTaskMapper baseMapper) {
        super(baseMapper);
    }
}


