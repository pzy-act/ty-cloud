package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuGuardian;
import com.vip.tycloud.repository.student.TyStuGuardianRepository;
import com.vip.tycloud.service.student.TyStuGuardianService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 监护人管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuGuardianServiceImpl implements TyStuGuardianService {

    private final TyStuGuardianRepository tyStuGuardianRepository;

    @Override
    public TyStuGuardian getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuGuardianRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuGuardian> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyStuGuardian> page = new Page<>(current, size);
        IPage<TyStuGuardian> pageResult = tyStuGuardianRepository.page(
            page,
            Wrappers.<TyStuGuardian>lambdaQuery()
                .eq(TyStuGuardian::getIsDeleted, 0)
                .orderByDesc(TyStuGuardian::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyStuGuardian entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyStuGuardianRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyStuGuardian entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyStuGuardianRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyStuGuardianRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


