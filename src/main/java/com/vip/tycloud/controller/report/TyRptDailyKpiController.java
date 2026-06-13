package com.vip.tycloud.controller.report;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.report.TyRptDailyKpiCreateReqDTO;
import com.vip.tycloud.dto.report.TyRptDailyKpiRespDTO;
import com.vip.tycloud.dto.report.TyRptDailyKpiUpdateReqDTO;
import com.vip.tycloud.entity.report.TyRptDailyKpi;
import com.vip.tycloud.service.report.TyRptDailyKpiService;
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
 * 数据报表 功能模块 - 每日经营看板 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report/daily-kpi")
public class TyRptDailyKpiController {

    private final TyRptDailyKpiService tyRptDailyKpiService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyRptDailyKpiCreateReqDTO req) {
        return ApiResponse.success(tyRptDailyKpiService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyRptDailyKpiUpdateReqDTO req) {
        return ApiResponse.success(tyRptDailyKpiService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyRptDailyKpiRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyRptDailyKpiService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyRptDailyKpiRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyRptDailyKpi> pageResult = tyRptDailyKpiService.page(req.getPageNumber(), req.getPageSize(), req.getKeyword(), req.getStatus());
        List<TyRptDailyKpiRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyRptDailyKpiService.deleteById(id, actualOperatorId));
    }

    private TyRptDailyKpi toEntity(TyRptDailyKpiCreateReqDTO req) {
        TyRptDailyKpi entity = new TyRptDailyKpi();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyRptDailyKpi toEntity(TyRptDailyKpiUpdateReqDTO req) {
        TyRptDailyKpi entity = new TyRptDailyKpi();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyRptDailyKpiRespDTO toRespDTO(TyRptDailyKpi entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyRptDailyKpiRespDTO respDTO = new TyRptDailyKpiRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


