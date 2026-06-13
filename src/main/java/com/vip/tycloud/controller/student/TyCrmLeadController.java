package com.vip.tycloud.controller.student;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.student.TyCrmLeadCreateReqDTO;
import com.vip.tycloud.dto.student.TyCrmLeadRespDTO;
import com.vip.tycloud.dto.student.TyCrmLeadUpdateReqDTO;
import com.vip.tycloud.entity.student.TyCrmLead;
import com.vip.tycloud.service.student.TyCrmLeadService;
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
 * 学员管理 功能模块 - 线索管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/lead")
public class TyCrmLeadController {

    private final TyCrmLeadService tyCrmLeadService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyCrmLeadCreateReqDTO req) {
        return ApiResponse.success(tyCrmLeadService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyCrmLeadUpdateReqDTO req) {
        return ApiResponse.success(tyCrmLeadService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyCrmLeadRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyCrmLeadService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyCrmLeadRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyCrmLead> pageResult = tyCrmLeadService.page(req.getPageNumber(), req.getPageSize());
        List<TyCrmLeadRespDTO> records = pageResult.getRecords().stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(PageResultDTO.of(pageResult.getTotal(), records));
    }

    @PostMapping("/{id}/convert-to-student")
    public ApiResponse<Long> convertToStudent(@PathVariable Long id) {
        Long studentId = tyCrmLeadService.convertToStudent(id);
        if (Objects.isNull(studentId)) {
            return ApiResponse.fail("线索转学员失败");
        }
        return ApiResponse.success(studentId);
    }

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(
        @PathVariable Long id,
        @RequestParam(value = "operatorId", required = false) Long operatorId
    ) {
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return ApiResponse.success(tyCrmLeadService.deleteById(id, actualOperatorId));
    }

    private TyCrmLead toEntity(TyCrmLeadCreateReqDTO req) {
        TyCrmLead entity = new TyCrmLead();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmLead toEntity(TyCrmLeadUpdateReqDTO req) {
        TyCrmLead entity = new TyCrmLead();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmLeadRespDTO toRespDTO(TyCrmLead entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyCrmLeadRespDTO respDTO = new TyCrmLeadRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


