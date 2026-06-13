package com.vip.tycloud.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyMessageTaskStatusEnum;
import com.vip.tycloud.util.PageQueryUtils;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.entity.message.TyMsgTask;
import com.vip.tycloud.repository.message.TyMsgTaskRepository;
import com.vip.tycloud.service.message.TyMsgTaskService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知与互动 功能模块 - 消息任务 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyMsgTaskServiceImpl implements TyMsgTaskService {

    private final TyMsgTaskRepository tyMsgTaskRepository;

    @Override
    public TyMsgTask getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyMsgTaskRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyMsgTask> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyMsgTask> page = new Page<>(current, size);
        IPage<TyMsgTask> pageResult = tyMsgTaskRepository.page(
            page,
            PageQueryUtils.baseQuery(keyword, status, "status", "task_no", "send_type", "target_type", "content_snapshot")
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }
    @Override
    public boolean save(TyMsgTask entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyMsgTaskRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyMsgTask entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyMsgTaskRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyMsgTaskRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


