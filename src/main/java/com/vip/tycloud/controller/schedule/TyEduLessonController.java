package com.vip.tycloud.controller.schedule;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.schedule.TyEduLessonCreateReqDTO;
import com.vip.tycloud.dto.schedule.TyEduLessonRespDTO;
import com.vip.tycloud.dto.schedule.TyEduLessonUpdateReqDTO;
import com.vip.tycloud.entity.schedule.TyEduLesson;
import com.vip.tycloud.service.schedule.TyEduLessonService;
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
 * 排课与资源 功能模块 - 课表管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/lesson")
public class TyEduLessonController {

    private final TyEduLessonService tyEduLessonService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyEduLessonCreateReqDTO req) {
        return ApiResponse.success(tyEduLessonService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyEduLessonUpdateReqDTO req) {
        return ApiResponse.success(tyEduLessonService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyEduLessonRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyEduLessonService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyEduLessonRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyEduLesson> pageResult = tyEduLessonService.page(req.getPageNumber(), req.getPageSize());
        List<TyEduLessonRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyEduLessonService.deleteById(id, actualOperatorId));
    }

    private TyEduLesson toEntity(TyEduLessonCreateReqDTO req) {
        TyEduLesson entity = new TyEduLesson();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduLesson toEntity(TyEduLessonUpdateReqDTO req) {
        TyEduLesson entity = new TyEduLesson();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduLessonRespDTO toRespDTO(TyEduLesson entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyEduLessonRespDTO respDTO = new TyEduLessonRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


