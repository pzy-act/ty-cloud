package com.vip.tycloud.controller.attendance;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.attendance.TyEduMakeupRecordCreateReqDTO;
import com.vip.tycloud.dto.attendance.TyEduMakeupRecordRespDTO;
import com.vip.tycloud.dto.attendance.TyEduMakeupRecordUpdateReqDTO;
import com.vip.tycloud.entity.attendance.TyEduMakeupRecord;
import com.vip.tycloud.service.attendance.TyEduMakeupRecordService;
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
 * 考勤与消课 功能模块 - 补课管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance/makeup-record")
public class TyEduMakeupRecordController {

    private final TyEduMakeupRecordService tyEduMakeupRecordService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyEduMakeupRecordCreateReqDTO req) {
        return ApiResponse.success(tyEduMakeupRecordService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyEduMakeupRecordUpdateReqDTO req) {
        return ApiResponse.success(tyEduMakeupRecordService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyEduMakeupRecordRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyEduMakeupRecordService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyEduMakeupRecordRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyEduMakeupRecord> pageResult = tyEduMakeupRecordService.page(req.getPageNumber(), req.getPageSize());
        List<TyEduMakeupRecordRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyEduMakeupRecordService.deleteById(id, actualOperatorId));
    }

    private TyEduMakeupRecord toEntity(TyEduMakeupRecordCreateReqDTO req) {
        TyEduMakeupRecord entity = new TyEduMakeupRecord();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduMakeupRecord toEntity(TyEduMakeupRecordUpdateReqDTO req) {
        TyEduMakeupRecord entity = new TyEduMakeupRecord();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduMakeupRecordRespDTO toRespDTO(TyEduMakeupRecord entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyEduMakeupRecordRespDTO respDTO = new TyEduMakeupRecordRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


