package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudent;
import com.vip.tycloud.repository.student.TyStuStudentRepository;
import com.vip.tycloud.service.student.TyStuStudentService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 学员档案 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuStudentServiceImpl implements TyStuStudentService {

    private final TyStuStudentRepository tyStuStudentRepository;

    @Override
    public TyStuStudent getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuStudentRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuStudent> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyStuStudent> page = new Page<>(current, size);
        IPage<TyStuStudent> pageResult = tyStuStudentRepository.page(
            page,
            Wrappers.<TyStuStudent>lambdaQuery()
                .eq(TyStuStudent::getIsDeleted, 0)
                .orderByDesc(TyStuStudent::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyStuStudent entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyStuStudentRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyStuStudent entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyStuStudentRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyStuStudentRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


