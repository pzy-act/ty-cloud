package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvMaterialTxn;
import com.vip.tycloud.repository.inventory.TyInvMaterialTxnRepository;
import com.vip.tycloud.service.inventory.TyInvMaterialTxnService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 库存流水 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvMaterialTxnServiceImpl implements TyInvMaterialTxnService {

    private final TyInvMaterialTxnRepository tyInvMaterialTxnRepository;

    @Override
    public TyInvMaterialTxn getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvMaterialTxnRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvMaterialTxn> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvMaterialTxn> page = new Page<>(current, size);
        IPage<TyInvMaterialTxn> pageResult = tyInvMaterialTxnRepository.page(
            page,
            Wrappers.<TyInvMaterialTxn>lambdaQuery()
                .eq(TyInvMaterialTxn::getIsDeleted, 0)
                .orderByDesc(TyInvMaterialTxn::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyInvMaterialTxn entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvMaterialTxnRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvMaterialTxn entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvMaterialTxnRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvMaterialTxnRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


