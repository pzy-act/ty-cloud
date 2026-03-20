package com.vip.tycloud.service.contract.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.contract.TyBizContract;
import com.vip.tycloud.repository.contract.TyBizContractRepository;
import com.vip.tycloud.service.contract.TyBizContractService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 报名与合同 功能模块 - 报名合同 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyBizContractServiceImpl implements TyBizContractService {

    private final TyBizContractRepository tyBizContractRepository;

    @Override
    public TyBizContract getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyBizContractRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyBizContract> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyBizContract> page = new Page<>(current, size);
        IPage<TyBizContract> pageResult = tyBizContractRepository.page(
            page,
            Wrappers.<TyBizContract>lambdaQuery()
                .eq(TyBizContract::getIsDeleted, 0)
                .orderByDesc(TyBizContract::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyBizContract entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyBizContractRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyBizContract entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyBizContractRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyBizContractRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


