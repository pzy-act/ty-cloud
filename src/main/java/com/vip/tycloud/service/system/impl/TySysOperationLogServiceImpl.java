package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysOperationLog;
import com.vip.tycloud.repository.system.TySysOperationLogRepository;
import com.vip.tycloud.service.system.TySysOperationLogService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 操作日志 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysOperationLogServiceImpl implements TySysOperationLogService {

    private final TySysOperationLogRepository tySysOperationLogRepository;

    @Override
    public TySysOperationLog getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysOperationLogRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysOperationLog> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysOperationLog> page = new Page<>(current, size);
        IPage<TySysOperationLog> pageResult = tySysOperationLogRepository.page(
            page,
            Wrappers.<TySysOperationLog>lambdaQuery()
                .eq(TySysOperationLog::getIsDeleted, 0)
                .orderByDesc(TySysOperationLog::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysOperationLog entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysOperationLogRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysOperationLog entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysOperationLogRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysOperationLogRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


