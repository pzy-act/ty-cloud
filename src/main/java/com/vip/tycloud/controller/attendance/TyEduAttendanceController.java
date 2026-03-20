package com.vip.tycloud.controller.attendance;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.attendance.TyEduAttendanceCreateReqDTO;
import com.vip.tycloud.dto.attendance.TyEduAttendanceRespDTO;
import com.vip.tycloud.dto.attendance.TyEduAttendanceUpdateReqDTO;
import com.vip.tycloud.entity.attendance.TyEduAttendance;
import com.vip.tycloud.service.attendance.TyEduAttendanceService;
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
 * 考勤与消课 功能模块 - 签到考勤 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance/attendance")
public class TyEduAttendanceController {

    private final TyEduAttendanceService tyEduAttendanceService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyEduAttendanceCreateReqDTO req) {
        return ApiResponse.success(tyEduAttendanceService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyEduAttendanceUpdateReqDTO req) {
        return ApiResponse.success(tyEduAttendanceService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyEduAttendanceRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyEduAttendanceService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyEduAttendanceRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyEduAttendance> pageResult = tyEduAttendanceService.page(req.getPageNumber(), req.getPageSize());
        List<TyEduAttendanceRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyEduAttendanceService.deleteById(id, actualOperatorId));
    }

    private TyEduAttendance toEntity(TyEduAttendanceCreateReqDTO req) {
        TyEduAttendance entity = new TyEduAttendance();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduAttendance toEntity(TyEduAttendanceUpdateReqDTO req) {
        TyEduAttendance entity = new TyEduAttendance();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduAttendanceRespDTO toRespDTO(TyEduAttendance entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyEduAttendanceRespDTO respDTO = new TyEduAttendanceRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


