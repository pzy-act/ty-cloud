package com.vip.tycloud.controller.schedule;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.schedule.TyResResourceBookingCreateReqDTO;
import com.vip.tycloud.dto.schedule.TyResResourceBookingRespDTO;
import com.vip.tycloud.dto.schedule.TyResResourceBookingUpdateReqDTO;
import com.vip.tycloud.entity.schedule.TyResResourceBooking;
import com.vip.tycloud.service.schedule.TyResResourceBookingService;
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
 * 排课与资源 功能模块 - 资源预约 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/resource-booking")
public class TyResResourceBookingController {

    private final TyResResourceBookingService tyResResourceBookingService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyResResourceBookingCreateReqDTO req) {
        return ApiResponse.success(tyResResourceBookingService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyResResourceBookingUpdateReqDTO req) {
        return ApiResponse.success(tyResResourceBookingService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyResResourceBookingRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyResResourceBookingService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyResResourceBookingRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyResResourceBooking> pageResult = tyResResourceBookingService.page(req.getPageNumber(), req.getPageSize());
        List<TyResResourceBookingRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyResResourceBookingService.deleteById(id, actualOperatorId));
    }

    private TyResResourceBooking toEntity(TyResResourceBookingCreateReqDTO req) {
        TyResResourceBooking entity = new TyResResourceBooking();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResResourceBooking toEntity(TyResResourceBookingUpdateReqDTO req) {
        TyResResourceBooking entity = new TyResResourceBooking();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResResourceBookingRespDTO toRespDTO(TyResResourceBooking entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyResResourceBookingRespDTO respDTO = new TyResResourceBookingRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


