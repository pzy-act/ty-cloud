package com.vip.tycloud.service.teacher.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyBaseStatusEnum;
import com.vip.tycloud.util.PageQueryUtils;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.entity.teacher.TyHrCommissionRule;
import com.vip.tycloud.repository.teacher.TyHrCommissionRuleRepository;
import com.vip.tycloud.service.teacher.TyHrCommissionRuleService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 教师与绩效 功能模块 - 提成规则 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyHrCommissionRuleServiceImpl implements TyHrCommissionRuleService {

    private final TyHrCommissionRuleRepository tyHrCommissionRuleRepository;

    @Override
    public TyHrCommissionRule getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyHrCommissionRuleRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyHrCommissionRule> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyHrCommissionRule> page = new Page<>(current, size);
        IPage<TyHrCommissionRule> pageResult = tyHrCommissionRuleRepository.page(
            page,
            PageQueryUtils.baseQuery(keyword, status, "status", "rule_name", "rule_type", "calc_formula")
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }
    @Override
    public boolean save(TyHrCommissionRule entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyHrCommissionRuleRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyHrCommissionRule entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyHrCommissionRuleRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyHrCommissionRuleRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


