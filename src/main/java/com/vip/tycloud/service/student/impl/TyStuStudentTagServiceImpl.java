package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudentTag;
import com.vip.tycloud.repository.student.TyStuStudentTagRepository;
import com.vip.tycloud.service.student.TyStuStudentTagService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 学员标签关联 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuStudentTagServiceImpl implements TyStuStudentTagService {

    private final TyStuStudentTagRepository tyStuStudentTagRepository;

    @Override
    public TyStuStudentTag getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuStudentTagRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuStudentTag> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyStuStudentTag> page = new Page<>(current, size);
        IPage<TyStuStudentTag> pageResult = tyStuStudentTagRepository.page(
            page,
            Wrappers.<TyStuStudentTag>lambdaQuery()
                .eq(TyStuStudentTag::getIsDeleted, 0)
                .orderByDesc(TyStuStudentTag::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyStuStudentTag entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyStuStudentTagRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyStuStudentTag entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyStuStudentTagRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyStuStudentTagRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


