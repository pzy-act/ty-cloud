package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyCrmLeadFollow;
import com.vip.tycloud.repository.student.TyCrmLeadFollowRepository;
import com.vip.tycloud.service.student.TyCrmLeadFollowService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 线索跟进 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyCrmLeadFollowServiceImpl implements TyCrmLeadFollowService {

    private final TyCrmLeadFollowRepository tyCrmLeadFollowRepository;

    @Override
    public TyCrmLeadFollow getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyCrmLeadFollowRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyCrmLeadFollow> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyCrmLeadFollow> page = new Page<>(current, size);
        IPage<TyCrmLeadFollow> pageResult = tyCrmLeadFollowRepository.page(
            page,
            Wrappers.<TyCrmLeadFollow>lambdaQuery()
                .eq(TyCrmLeadFollow::getIsDeleted, 0)
                .orderByDesc(TyCrmLeadFollow::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyCrmLeadFollow entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyCrmLeadFollowRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyCrmLeadFollow entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyCrmLeadFollowRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyCrmLeadFollowRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


