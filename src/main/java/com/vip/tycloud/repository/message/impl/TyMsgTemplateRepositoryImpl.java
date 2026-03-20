package com.vip.tycloud.repository.message.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.message.TyMsgTemplate;
import com.vip.tycloud.mapper.message.TyMsgTemplateMapper;
import com.vip.tycloud.repository.message.TyMsgTemplateRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知与互动 功能模块 - 消息模板 - 仓储实现类。
 */
@Repository
public class TyMsgTemplateRepositoryImpl extends BaseRepositoryImpl<TyMsgTemplateMapper, TyMsgTemplate> implements TyMsgTemplateRepository {

    public TyMsgTemplateRepositoryImpl(TyMsgTemplateMapper baseMapper) {
        super(baseMapper);
    }
}


