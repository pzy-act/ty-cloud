package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyCrmLead;
import com.vip.tycloud.repository.student.TyCrmLeadRepository;
import com.vip.tycloud.service.student.TyCrmLeadService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 线索管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyCrmLeadServiceImpl implements TyCrmLeadService {

    private final TyCrmLeadRepository tyCrmLeadRepository;

    @Override
    public TyCrmLead getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyCrmLeadRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyCrmLead> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyCrmLead> page = new Page<>(current, size);
        IPage<TyCrmLead> pageResult = tyCrmLeadRepository.page(
            page,
            Wrappers.<TyCrmLead>lambdaQuery()
                .eq(TyCrmLead::getIsDeleted, 0)
                .orderByDesc(TyCrmLead::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyCrmLead entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyCrmLeadRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyCrmLead entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyCrmLeadRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyCrmLeadRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


