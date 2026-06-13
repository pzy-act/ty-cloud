package com.vip.tycloud.service.finance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyOrderStatusEnum;
import com.vip.tycloud.entity.finance.TyFinOrder;
import com.vip.tycloud.repository.finance.TyFinOrderRepository;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.service.finance.TyFinOrderService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 收费与订单 功能模块 - 收费订单 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyFinOrderServiceImpl implements TyFinOrderService {

    private final TyFinOrderRepository tyFinOrderRepository;

    @Override
    public TyFinOrder getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyFinOrderRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyFinOrder> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyFinOrder> page = new Page<>(current, size);
        LambdaQueryWrapper<TyFinOrder> queryWrapper = Wrappers.<TyFinOrder>lambdaQuery()
            .eq(TyFinOrder::getIsDeleted, 0)
            .eq(Objects.nonNull(status), TyFinOrder::getStatus, status);
        if (StringUtils.hasText(keyword)) {
            String actualKeyword = keyword.trim();
            queryWrapper.and(wrapper -> wrapper
                .like(TyFinOrder::getOrderNo, actualKeyword)
                .or()
                .like(TyFinOrder::getBusinessType, actualKeyword)
            );
        }
        IPage<TyFinOrder> pageResult = tyFinOrderRepository.page(
            page,
            queryWrapper.orderByDesc(TyFinOrder::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyFinOrder entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyFinOrderRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyFinOrder entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyFinOrderRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyFinOrderRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


