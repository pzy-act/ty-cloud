package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysOperationLog;
import com.vip.tycloud.mapper.system.TySysOperationLogMapper;
import com.vip.tycloud.repository.system.TySysOperationLogRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 操作日志 - 仓储实现类。
 */
@Repository
public class TySysOperationLogRepositoryImpl extends BaseRepositoryImpl<TySysOperationLogMapper, TySysOperationLog> implements TySysOperationLogRepository {

    public TySysOperationLogRepositoryImpl(TySysOperationLogMapper baseMapper) {
        super(baseMapper);
    }
}


