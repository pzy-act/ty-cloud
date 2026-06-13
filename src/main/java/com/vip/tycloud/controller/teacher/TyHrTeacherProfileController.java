package com.vip.tycloud.controller.teacher;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherProfileCreateReqDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherProfileRespDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherProfileUpdateReqDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherProfile;
import com.vip.tycloud.service.teacher.TyHrTeacherProfileService;
import jakarta.validation.Valid;
import java.util.Objects;
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
 * 教师与绩效 功能模块 - 教师档案 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/teacher-profile")
public class TyHrTeacherProfileController {

    private final TyHrTeacherProfileService tyHrTeacherProfileService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyHrTeacherProfileCreateReqDTO req) {
        return ApiResponse.success(tyHrTeacherProfileService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyHrTeacherProfileUpdateReqDTO req) {
        return ApiResponse.success(tyHrTeacherProfileService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyHrTeacherProfileRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(tyHrTeacherProfileService.getRespById(id));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyHrTeacherProfileRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        return ApiResponse.success(tyHrTeacherProfileService.pageResp(req.getPageNumber(), req.getPageSize()));
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
        return ApiResponse.success(tyHrTeacherProfileService.deleteById(id, actualOperatorId));
    }

    private TyHrTeacherProfile toEntity(TyHrTeacherProfileCreateReqDTO req) {
        TyHrTeacherProfile entity = new TyHrTeacherProfile();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyHrTeacherProfile toEntity(TyHrTeacherProfileUpdateReqDTO req) {
        TyHrTeacherProfile entity = new TyHrTeacherProfile();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

}


