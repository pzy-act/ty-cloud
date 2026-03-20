package com.vip.tycloud.controller.teacher;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherPayrollCreateReqDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherPayrollRespDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherPayrollUpdateReqDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherPayroll;
import com.vip.tycloud.service.teacher.TyHrTeacherPayrollService;
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
 * 教师与绩效 功能模块 - 工资核算 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/teacher-payroll")
public class TyHrTeacherPayrollController {

    private final TyHrTeacherPayrollService tyHrTeacherPayrollService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyHrTeacherPayrollCreateReqDTO req) {
        return ApiResponse.success(tyHrTeacherPayrollService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyHrTeacherPayrollUpdateReqDTO req) {
        return ApiResponse.success(tyHrTeacherPayrollService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyHrTeacherPayrollRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyHrTeacherPayrollService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyHrTeacherPayrollRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyHrTeacherPayroll> pageResult = tyHrTeacherPayrollService.page(req.getPageNumber(), req.getPageSize());
        List<TyHrTeacherPayrollRespDTO> records = pageResult.getRecords().stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(PageResultDTO.of(pageResult.getTotal(), records));
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
        return ApiResponse.success(tyHrTeacherPayrollService.deleteById(id, actualOperatorId));
    }

    private TyHrTeacherPayroll toEntity(TyHrTeacherPayrollCreateReqDTO req) {
        TyHrTeacherPayroll entity = new TyHrTeacherPayroll();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyHrTeacherPayroll toEntity(TyHrTeacherPayrollUpdateReqDTO req) {
        TyHrTeacherPayroll entity = new TyHrTeacherPayroll();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyHrTeacherPayrollRespDTO toRespDTO(TyHrTeacherPayroll entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyHrTeacherPayrollRespDTO respDTO = new TyHrTeacherPayrollRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


