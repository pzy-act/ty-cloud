package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyCrmLeadFollow;
import com.vip.tycloud.mapper.student.TyCrmLeadFollowMapper;
import com.vip.tycloud.repository.student.TyCrmLeadFollowRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 线索跟进 - 仓储实现类。
 */
@Repository
public class TyCrmLeadFollowRepositoryImpl extends BaseRepositoryImpl<TyCrmLeadFollowMapper, TyCrmLeadFollow> implements TyCrmLeadFollowRepository {

    public TyCrmLeadFollowRepositoryImpl(TyCrmLeadFollowMapper baseMapper) {
        super(baseMapper);
    }
}


