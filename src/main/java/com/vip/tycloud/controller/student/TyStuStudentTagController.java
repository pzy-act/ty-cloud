package com.vip.tycloud.controller.student;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.dto.student.TyStuStudentTagBindReqDTO;
import com.vip.tycloud.dto.student.TyStuStudentTagRespDTO;
import com.vip.tycloud.entity.student.TyStuStudentTag;
import com.vip.tycloud.entity.student.TyStuTag;
import com.vip.tycloud.service.student.TyStuStudentTagService;
import com.vip.tycloud.service.student.TyStuTagService;
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
 * Student tag relation controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/student-tag")
public class TyStuStudentTagController {

    private final TyStuStudentTagService tyStuStudentTagService;

    private final TyStuTagService tyStuTagService;

    @GetMapping("/student/{studentId}")
    public ApiResponse<List<TyStuStudentTagRespDTO>> listByStudent(@PathVariable Long studentId) {
        List<TyStuStudentTagRespDTO> records = tyStuStudentTagService.listByStudentId(studentId).stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(records);
    }

    @PostMapping("/bind")
    public ApiResponse<Boolean> bind(@Valid @RequestBody TyStuStudentTagBindReqDTO req) {
        TyStuStudentTag entity = new TyStuStudentTag();
        BeanUtils.copyProperties(req, entity);
        return ApiResponse.success(tyStuStudentTagService.bind(entity));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(
        @PathVariable Long id,
        @RequestParam(value = "operatorId", required = false) Long operatorId
    ) {
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return ApiResponse.success(tyStuStudentTagService.deleteById(id, actualOperatorId));
    }

    private TyStuStudentTagRespDTO toRespDTO(TyStuStudentTag entity) {
        TyStuStudentTagRespDTO respDTO = new TyStuStudentTagRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        TyStuTag tag = tyStuTagService.getById(entity.getTagId());
        if (Objects.nonNull(tag)) {
            respDTO.setTagName(tag.getTagName());
            respDTO.setTagType(tag.getTagType());
            respDTO.setColor(tag.getColor());
            respDTO.setStatus(tag.getStatus());
        }
        return respDTO;
    }
}
