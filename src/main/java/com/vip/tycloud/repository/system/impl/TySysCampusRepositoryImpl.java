package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysCampus;
import com.vip.tycloud.mapper.system.TySysCampusMapper;
import com.vip.tycloud.repository.system.TySysCampusRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 校区管理 - 仓储实现类。
 */
@Repository
public class TySysCampusRepositoryImpl extends BaseRepositoryImpl<TySysCampusMapper, TySysCampus> implements TySysCampusRepository {

    public TySysCampusRepositoryImpl(TySysCampusMapper baseMapper) {
        super(baseMapper);
    }
}


