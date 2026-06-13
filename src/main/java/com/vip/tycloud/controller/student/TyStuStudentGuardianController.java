package com.vip.tycloud.controller.student;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.dto.student.TyStuStudentGuardianBindReqDTO;
import com.vip.tycloud.dto.student.TyStuStudentGuardianRespDTO;
import com.vip.tycloud.entity.student.TyStuGuardian;
import com.vip.tycloud.entity.student.TyStuStudentGuardian;
import com.vip.tycloud.service.student.TyStuGuardianService;
import com.vip.tycloud.service.student.TyStuStudentGuardianService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Student guardian relation controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/student-guardian")
public class TyStuStudentGuardianController {

    private final TyStuStudentGuardianService tyStuStudentGuardianService;

    private final TyStuGuardianService tyStuGuardianService;

    @GetMapping("/student/{studentId}")
    public ApiResponse<List<TyStuStudentGuardianRespDTO>> listByStudent(@PathVariable Long studentId) {
        List<TyStuStudentGuardianRespDTO> records = tyStuStudentGuardianService.listByStudentId(studentId).stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(records);
    }

    @PostMapping("/bind")
    public ApiResponse<Boolean> bind(@Valid @RequestBody TyStuStudentGuardianBindReqDTO req) {
        TyStuStudentGuardian entity = new TyStuStudentGuardian();
        BeanUtils.copyProperties(req, entity);
        return ApiResponse.success(tyStuStudentGuardianService.bind(entity));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(
        @PathVariable Long id,
        @RequestParam(value = "operatorId", required = false) Long operatorId
    ) {
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return ApiResponse.success(tyStuStudentGuardianService.deleteById(id, actualOperatorId));
    }

    private TyStuStudentGuardianRespDTO toRespDTO(TyStuStudentGuardian entity) {
        TyStuStudentGuardianRespDTO respDTO = new TyStuStudentGuardianRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        TyStuGuardian guardian = tyStuGuardianService.getById(entity.getGuardianId());
        if (Objects.nonNull(guardian)) {
            respDTO.setGuardianName(guardian.getGuardianName());
            respDTO.setRelationType(guardian.getRelationType());
            respDTO.setMobile(guardian.getMobile());
            respDTO.setWechat(guardian.getWechat());
            respDTO.setStatus(guardian.getStatus());
        }
        return respDTO;
    }
}
