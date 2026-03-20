package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyCrmChannel;
import com.vip.tycloud.mapper.student.TyCrmChannelMapper;
import com.vip.tycloud.repository.student.TyCrmChannelRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 渠道管理 - 仓储实现类。
 */
@Repository
public class TyCrmChannelRepositoryImpl extends BaseRepositoryImpl<TyCrmChannelMapper, TyCrmChannel> implements TyCrmChannelRepository {

    public TyCrmChannelRepositoryImpl(TyCrmChannelMapper baseMapper) {
        super(baseMapper);
    }
}


