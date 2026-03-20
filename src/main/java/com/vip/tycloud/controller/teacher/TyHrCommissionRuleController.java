package com.vip.tycloud.controller.teacher;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.teacher.TyHrCommissionRuleCreateReqDTO;
import com.vip.tycloud.dto.teacher.TyHrCommissionRuleRespDTO;
import com.vip.tycloud.dto.teacher.TyHrCommissionRuleUpdateReqDTO;
import com.vip.tycloud.entity.teacher.TyHrCommissionRule;
import com.vip.tycloud.service.teacher.TyHrCommissionRuleService;
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
 * 教师与绩效 功能模块 - 提成规则 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/commission-rule")
public class TyHrCommissionRuleController {

    private final TyHrCommissionRuleService tyHrCommissionRuleService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyHrCommissionRuleCreateReqDTO req) {
        return ApiResponse.success(tyHrCommissionRuleService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyHrCommissionRuleUpdateReqDTO req) {
        return ApiResponse.success(tyHrCommissionRuleService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyHrCommissionRuleRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyHrCommissionRuleService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyHrCommissionRuleRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyHrCommissionRule> pageResult = tyHrCommissionRuleService.page(req.getPageNumber(), req.getPageSize());
        List<TyHrCommissionRuleRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyHrCommissionRuleService.deleteById(id, actualOperatorId));
    }

    private TyHrCommissionRule toEntity(TyHrCommissionRuleCreateReqDTO req) {
        TyHrCommissionRule entity = new TyHrCommissionRule();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyHrCommissionRule toEntity(TyHrCommissionRuleUpdateReqDTO req) {
        TyHrCommissionRule entity = new TyHrCommissionRule();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyHrCommissionRuleRespDTO toRespDTO(TyHrCommissionRule entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyHrCommissionRuleRespDTO respDTO = new TyHrCommissionRuleRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


