package com.vip.tycloud.controller.message;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.message.TyMsgTemplateCreateReqDTO;
import com.vip.tycloud.dto.message.TyMsgTemplateRespDTO;
import com.vip.tycloud.dto.message.TyMsgTemplateUpdateReqDTO;
import com.vip.tycloud.entity.message.TyMsgTemplate;
import com.vip.tycloud.service.message.TyMsgTemplateService;
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
 * 通知与互动 功能模块 - 消息模板 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message/template")
public class TyMsgTemplateController {

    private final TyMsgTemplateService tyMsgTemplateService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyMsgTemplateCreateReqDTO req) {
        return ApiResponse.success(tyMsgTemplateService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyMsgTemplateUpdateReqDTO req) {
        return ApiResponse.success(tyMsgTemplateService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyMsgTemplateRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyMsgTemplateService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyMsgTemplateRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyMsgTemplate> pageResult = tyMsgTemplateService.page(req.getPageNumber(), req.getPageSize(), req.getKeyword(), req.getStatus());
        List<TyMsgTemplateRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyMsgTemplateService.deleteById(id, actualOperatorId));
    }

    private TyMsgTemplate toEntity(TyMsgTemplateCreateReqDTO req) {
        TyMsgTemplate entity = new TyMsgTemplate();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyMsgTemplate toEntity(TyMsgTemplateUpdateReqDTO req) {
        TyMsgTemplate entity = new TyMsgTemplate();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyMsgTemplateRespDTO toRespDTO(TyMsgTemplate entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyMsgTemplateRespDTO respDTO = new TyMsgTemplateRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


