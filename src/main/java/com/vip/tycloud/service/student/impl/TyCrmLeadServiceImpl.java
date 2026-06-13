package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyCrmLeadStatusEnum;
import com.vip.tycloud.entity.student.TyCrmLead;
import com.vip.tycloud.entity.student.TyCrmLeadFollow;
import com.vip.tycloud.entity.student.TyStuStudent;
import com.vip.tycloud.repository.student.TyCrmLeadFollowRepository;
import com.vip.tycloud.repository.student.TyCrmLeadRepository;
import com.vip.tycloud.repository.student.TyStuStudentRepository;
import com.vip.tycloud.service.student.TyCrmLeadService;
import com.vip.tycloud.util.SecurityContextUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 学员管理 功能模块 - 线索管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyCrmLeadServiceImpl implements TyCrmLeadService {

    private final TyCrmLeadRepository tyCrmLeadRepository;

    private final TyStuStudentRepository tyStuStudentRepository;

    private final TyCrmLeadFollowRepository tyCrmLeadFollowRepository;

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
    @Transactional(rollbackFor = Exception.class)
    public Long convertToStudent(Long leadId) {
        TyCrmLead lead = getById(leadId);
        if (Objects.isNull(lead) || Objects.equals(lead.getIsDeleted(), 1)) {
            return null;
        }
        TyStuStudent existingStudent = findExistingStudent(lead);
        if (Objects.nonNull(existingStudent)) {
            saveConvertFollow(lead, existingStudent.getId(), true);
            markLeadConverted(lead.getId());
            return existingStudent.getId();
        }
        TyStuStudent student = new TyStuStudent();
        student.setCampusId(lead.getCampusId());
        student.setStudentName(lead.getLeadName());
        student.setGender(lead.getGender());
        student.setMobile(lead.getMobile());
        student.setLevel(formatIntentLevel(lead.getIntentLevel()));
        student.setStatus(1);
        student.setFirstEnrollTime(LocalDateTime.now());
        student.setIsDeleted(0);
        boolean saved = tyStuStudentRepository.save(student) > 0;
        if (!saved) {
            return null;
        }
        saveConvertFollow(lead, student.getId(), false);
        markLeadConverted(lead.getId());
        return student.getId();
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyCrmLeadRepository.logicDeleteById(id, actualOperatorId) > 0;
    }

    private TyStuStudent findExistingStudent(TyCrmLead lead) {
        if (!StringUtils.hasText(lead.getMobile())) {
            return null;
        }
        List<TyStuStudent> students = tyStuStudentRepository.list(
            Wrappers.<TyStuStudent>lambdaQuery()
                .eq(TyStuStudent::getMobile, lead.getMobile())
                .eq(TyStuStudent::getIsDeleted, 0)
                .orderByDesc(TyStuStudent::getId)
        );
        return students.isEmpty() ? null : students.get(0);
    }

    private void saveConvertFollow(TyCrmLead lead, Long studentId, boolean existing) {
        TyCrmLeadFollow follow = new TyCrmLeadFollow();
        follow.setCampusId(lead.getCampusId());
        follow.setLeadId(lead.getId());
        follow.setFollowType("转学员");
        follow.setContent(existing ? "线索已匹配已有学员，学员ID：" + studentId : "线索已转为学员，学员ID：" + studentId);
        follow.setFollowTime(LocalDateTime.now());
        Long currentUserId = SecurityContextUtils.getCurrentUserId();
        follow.setFollowerId(Objects.nonNull(currentUserId) ? currentUserId : lead.getOwnerUserId());
        follow.setIsDeleted(0);
        tyCrmLeadFollowRepository.save(follow);
    }

    private void markLeadConverted(Long leadId) {
        TyCrmLead lead = new TyCrmLead();
        lead.setId(leadId);
        lead.setStatus(TyCrmLeadStatusEnum.CONVERTED.getCode());
        tyCrmLeadRepository.updateById(lead);
    }

    private String formatIntentLevel(Integer intentLevel) {
        if (Objects.isNull(intentLevel)) {
            return null;
        }
        if (intentLevel >= 3) {
            return "高意向";
        }
        if (intentLevel == 2) {
            return "中意向";
        }
        return "低意向";
    }
}


