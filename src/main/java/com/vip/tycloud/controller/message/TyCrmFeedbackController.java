package com.vip.tycloud.controller.message;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.message.TyCrmFeedbackCreateReqDTO;
import com.vip.tycloud.dto.message.TyCrmFeedbackRespDTO;
import com.vip.tycloud.dto.message.TyCrmFeedbackUpdateReqDTO;
import com.vip.tycloud.entity.message.TyCrmFeedback;
import com.vip.tycloud.service.message.TyCrmFeedbackService;
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
 * 通知与互动 功能模块 - 家长反馈 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message/feedback")
public class TyCrmFeedbackController {

    private final TyCrmFeedbackService tyCrmFeedbackService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyCrmFeedbackCreateReqDTO req) {
        return ApiResponse.success(tyCrmFeedbackService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyCrmFeedbackUpdateReqDTO req) {
        return ApiResponse.success(tyCrmFeedbackService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyCrmFeedbackRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyCrmFeedbackService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyCrmFeedbackRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyCrmFeedback> pageResult = tyCrmFeedbackService.page(req.getPageNumber(), req.getPageSize());
        List<TyCrmFeedbackRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyCrmFeedbackService.deleteById(id, actualOperatorId));
    }

    private TyCrmFeedback toEntity(TyCrmFeedbackCreateReqDTO req) {
        TyCrmFeedback entity = new TyCrmFeedback();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmFeedback toEntity(TyCrmFeedbackUpdateReqDTO req) {
        TyCrmFeedback entity = new TyCrmFeedback();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyCrmFeedbackRespDTO toRespDTO(TyCrmFeedback entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyCrmFeedbackRespDTO respDTO = new TyCrmFeedbackRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


