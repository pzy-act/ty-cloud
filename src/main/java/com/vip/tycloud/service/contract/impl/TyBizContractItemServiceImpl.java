package com.vip.tycloud.service.contract.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.contract.TyBizContractItem;
import com.vip.tycloud.repository.contract.TyBizContractItemRepository;
import com.vip.tycloud.service.contract.TyBizContractItemService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 报名与合同 功能模块 - 合同明细 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyBizContractItemServiceImpl implements TyBizContractItemService {

    private final TyBizContractItemRepository tyBizContractItemRepository;

    @Override
    public TyBizContractItem getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyBizContractItemRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyBizContractItem> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyBizContractItem> page = new Page<>(current, size);
        IPage<TyBizContractItem> pageResult = tyBizContractItemRepository.page(
            page,
            Wrappers.<TyBizContractItem>lambdaQuery()
                .eq(TyBizContractItem::getIsDeleted, 0)
                .orderByDesc(TyBizContractItem::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyBizContractItem entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyBizContractItemRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyBizContractItem entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyBizContractItemRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyBizContractItemRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


