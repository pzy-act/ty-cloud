package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudentGuardian;
import com.vip.tycloud.repository.student.TyStuStudentGuardianRepository;
import com.vip.tycloud.service.student.TyStuStudentGuardianService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 学员监护关系 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuStudentGuardianServiceImpl implements TyStuStudentGuardianService {

    private final TyStuStudentGuardianRepository tyStuStudentGuardianRepository;

    @Override
    public TyStuStudentGuardian getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuStudentGuardianRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuStudentGuardian> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyStuStudentGuardian> page = new Page<>(current, size);
        IPage<TyStuStudentGuardian> pageResult = tyStuStudentGuardianRepository.page(
            page,
            Wrappers.<TyStuStudentGuardian>lambdaQuery()
                .eq(TyStuStudentGuardian::getIsDeleted, 0)
                .orderByDesc(TyStuStudentGuardian::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public List<TyStuStudentGuardian> listByStudentId(Long studentId) {
        if (Objects.isNull(studentId)) {
            return Collections.emptyList();
        }
        return tyStuStudentGuardianRepository.list(
            Wrappers.<TyStuStudentGuardian>lambdaQuery()
                .eq(TyStuStudentGuardian::getStudentId, studentId)
                .eq(TyStuStudentGuardian::getIsDeleted, 0)
                .orderByDesc(TyStuStudentGuardian::getIsPrimaryContact)
                .orderByDesc(TyStuStudentGuardian::getId)
        );
    }

    @Override
    public boolean save(TyStuStudentGuardian entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyStuStudentGuardianRepository.save(entity) > 0;
    }

    @Override
    public boolean bind(TyStuStudentGuardian entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getStudentId()) || Objects.isNull(entity.getGuardianId())) {
            return false;
        }
        List<TyStuStudentGuardian> existingRelations = tyStuStudentGuardianRepository.list(
            Wrappers.<TyStuStudentGuardian>lambdaQuery()
                .eq(TyStuStudentGuardian::getStudentId, entity.getStudentId())
                .eq(TyStuStudentGuardian::getGuardianId, entity.getGuardianId())
                .eq(TyStuStudentGuardian::getIsDeleted, 0)
        );
        if (!existingRelations.isEmpty()) {
            return true;
        }
        if (Objects.isNull(entity.getIsPrimaryContact())) {
            entity.setIsPrimaryContact(0);
        }
        if (Objects.equals(entity.getIsPrimaryContact(), 1)) {
            clearPrimaryContact(entity.getStudentId());
        }
        return save(entity);
    }

    @Override
    public boolean updateById(TyStuStudentGuardian entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyStuStudentGuardianRepository.updateById(entity) > 0;
    }

    private void clearPrimaryContact(Long studentId) {
        List<TyStuStudentGuardian> relations = listByStudentId(studentId);
        for (TyStuStudentGuardian relation : relations) {
            if (Objects.equals(relation.getIsPrimaryContact(), 1)) {
                TyStuStudentGuardian updateEntity = new TyStuStudentGuardian();
                updateEntity.setId(relation.getId());
                updateEntity.setIsPrimaryContact(0);
                tyStuStudentGuardianRepository.updateById(updateEntity);
            }
        }
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyStuStudentGuardianRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


