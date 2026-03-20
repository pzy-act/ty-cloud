package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysCampus;
import com.vip.tycloud.repository.system.TySysCampusRepository;
import com.vip.tycloud.service.system.TySysCampusService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 校区管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysCampusServiceImpl implements TySysCampusService {

    private final TySysCampusRepository tySysCampusRepository;

    @Override
    public TySysCampus getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysCampusRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysCampus> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysCampus> page = new Page<>(current, size);
        IPage<TySysCampus> pageResult = tySysCampusRepository.page(
            page,
            Wrappers.<TySysCampus>lambdaQuery()
                .eq(TySysCampus::getIsDeleted, 0)
                .orderByDesc(TySysCampus::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysCampus entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysCampusRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysCampus entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysCampusRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysCampusRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


