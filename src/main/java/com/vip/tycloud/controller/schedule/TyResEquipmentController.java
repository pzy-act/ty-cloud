package com.vip.tycloud.controller.schedule;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.schedule.TyResEquipmentCreateReqDTO;
import com.vip.tycloud.dto.schedule.TyResEquipmentRespDTO;
import com.vip.tycloud.dto.schedule.TyResEquipmentUpdateReqDTO;
import com.vip.tycloud.entity.schedule.TyResEquipment;
import com.vip.tycloud.service.schedule.TyResEquipmentService;
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
 * 排课与资源 功能模块 - 设备资源 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/equipment")
public class TyResEquipmentController {

    private final TyResEquipmentService tyResEquipmentService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyResEquipmentCreateReqDTO req) {
        return ApiResponse.success(tyResEquipmentService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyResEquipmentUpdateReqDTO req) {
        return ApiResponse.success(tyResEquipmentService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyResEquipmentRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyResEquipmentService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyResEquipmentRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyResEquipment> pageResult = tyResEquipmentService.page(req.getPageNumber(), req.getPageSize());
        List<TyResEquipmentRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyResEquipmentService.deleteById(id, actualOperatorId));
    }

    private TyResEquipment toEntity(TyResEquipmentCreateReqDTO req) {
        TyResEquipment entity = new TyResEquipment();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResEquipment toEntity(TyResEquipmentUpdateReqDTO req) {
        TyResEquipment entity = new TyResEquipment();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResEquipmentRespDTO toRespDTO(TyResEquipment entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyResEquipmentRespDTO respDTO = new TyResEquipmentRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


