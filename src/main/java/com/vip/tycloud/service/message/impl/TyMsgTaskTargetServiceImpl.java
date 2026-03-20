package com.vip.tycloud.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.message.TyMsgTaskTarget;
import com.vip.tycloud.repository.message.TyMsgTaskTargetRepository;
import com.vip.tycloud.service.message.TyMsgTaskTargetService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知与互动 功能模块 - 消息任务对象 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyMsgTaskTargetServiceImpl implements TyMsgTaskTargetService {

    private final TyMsgTaskTargetRepository tyMsgTaskTargetRepository;

    @Override
    public TyMsgTaskTarget getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyMsgTaskTargetRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyMsgTaskTarget> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyMsgTaskTarget> page = new Page<>(current, size);
        IPage<TyMsgTaskTarget> pageResult = tyMsgTaskTargetRepository.page(
            page,
            Wrappers.<TyMsgTaskTarget>lambdaQuery()
                .eq(TyMsgTaskTarget::getIsDeleted, 0)
                .orderByDesc(TyMsgTaskTarget::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyMsgTaskTarget entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyMsgTaskTargetRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyMsgTaskTarget entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyMsgTaskTargetRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyMsgTaskTargetRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


