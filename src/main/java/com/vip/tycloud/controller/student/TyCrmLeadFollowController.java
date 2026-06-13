package com.vip.tycloud.controller.student;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.student.TyCrmLeadFollowCreateReqDTO;
import com.vip.tycloud.dto.student.TyCrmLeadFollowRespDTO;
import com.vip.tycloud.dto.student.TyCrmLeadFollowUpdateReqDTO;
import com.vip.tycloud.entity.student.TyCrmLeadFollow;
import com.vip.tycloud.service.student.TyCrmLeadFollowService;
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
 * Lead follow controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/lead-follow")
public class TyCrmLeadFollowController {

    private final TyCrmLeadFollowService tyCrmLeadFollowService;

    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyCrmLeadFollowCreateReqDTO req) {
        return ApiResponse.success(tyCrmLeadFollowService.save(toEntity(req)));
    }

    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyCrmLeadFollowUpdateReqDTO req) {
        return ApiResponse.success(tyCrmLeadFollowService.updateById(toEntity(req)));
    }

    @GetMapping("/detail/{id}")
    public ApiResponse<TyCrmLeadFollowRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyCrmLeadFollowService.getById(id)));
    }

    @GetMapping("/lead/{leadId}")
    public ApiResponse<List<TyCrmLeadFollowRespDTO>> listByLead(@PathVariable Long leadId) {
        List<TyCrmLeadFollowRespDTO> records = tyCrmLeadFollowService.listByLeadId(leadId).stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(records);
    }

    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyCrmLeadFollowRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyCrmLeadFollow> pageResult = tyCrmLeadFollowService.page(req.getPageNumber(), req.getPageSize());
        List<TyCrmLeadFollowRespDTO> records = pageResult.getRecords().stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(PageResultDTO.of(pageResult.getTotal(), records));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(
        @PathVariable Long id,
        @RequestParam(value = "operatorId", required = false) Long operatorId
    ) {
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return ApiResponse.success(tyCrmLeadFollowService.deleteById(id, actualOperatorId));
    }

    private TyCrmLeadFollow toEntity(TyCrmLeadFollowCreateReqDTO req) {
        TyCrmLeadFollow entity = new TyCrmLeadFollow();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmLeadFollow toEntity(TyCrmLeadFollowUpdateReqDTO req) {
        TyCrmLeadFollow entity = new TyCrmLeadFollow();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmLeadFollowRespDTO toRespDTO(TyCrmLeadFollow entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyCrmLeadFollowRespDTO respDTO = new TyCrmLeadFollowRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}
