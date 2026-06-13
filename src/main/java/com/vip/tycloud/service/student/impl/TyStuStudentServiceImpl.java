package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudent;
import com.vip.tycloud.entity.student.TyStuStudentTag;
import com.vip.tycloud.repository.student.TyStuStudentRepository;
import com.vip.tycloud.repository.student.TyStuStudentTagRepository;
import com.vip.tycloud.service.student.TyStuStudentService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 学员管理 功能模块 - 学员档案 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuStudentServiceImpl implements TyStuStudentService {

    private final TyStuStudentRepository tyStuStudentRepository;

    private final TyStuStudentTagRepository tyStuStudentTagRepository;

    @Override
    public TyStuStudent getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuStudentRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuStudent> page(Integer pageNumber, Integer pageSize) {
        return page(pageNumber, pageSize, null, null, null);
    }

    @Override
    public PageResultDTO<TyStuStudent> page(Integer pageNumber, Integer pageSize, String keyword, Integer status, Long tagId) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        List<Long> studentIds = listStudentIdsByTagId(tagId);
        if (Objects.nonNull(tagId) && studentIds.isEmpty()) {
            return PageResultDTO.of(0L, Collections.emptyList());
        }
        Page<TyStuStudent> page = new Page<>(current, size);
        LambdaQueryWrapper<TyStuStudent> queryWrapper = Wrappers.<TyStuStudent>lambdaQuery()
            .eq(TyStuStudent::getIsDeleted, 0);
        if (Objects.nonNull(status)) {
            queryWrapper.eq(TyStuStudent::getStatus, status);
        }
        if (StringUtils.hasText(keyword)) {
            String actualKeyword = keyword.trim();
            queryWrapper.and(wrapper -> wrapper
                .like(TyStuStudent::getStudentNo, actualKeyword)
                .or()
                .like(TyStuStudent::getStudentName, actualKeyword)
                .or()
                .like(TyStuStudent::getMobile, actualKeyword)
                .or()
                .like(TyStuStudent::getSchool, actualKeyword)
                .or()
                .like(TyStuStudent::getLevel, actualKeyword)
            );
        }
        if (!studentIds.isEmpty()) {
            queryWrapper.in(TyStuStudent::getId, studentIds);
        }
        queryWrapper.orderByDesc(TyStuStudent::getId);
        IPage<TyStuStudent> pageResult = tyStuStudentRepository.page(page, queryWrapper);
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    private List<Long> listStudentIdsByTagId(Long tagId) {
        if (Objects.isNull(tagId)) {
            return Collections.emptyList();
        }
        return tyStuStudentTagRepository.list(
                Wrappers.<TyStuStudentTag>lambdaQuery()
                    .eq(TyStuStudentTag::getTagId, tagId)
                    .eq(TyStuStudentTag::getIsDeleted, 0)
            )
            .stream()
            .map(TyStuStudentTag::getStudentId)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
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


