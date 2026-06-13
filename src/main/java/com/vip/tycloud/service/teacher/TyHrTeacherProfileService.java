package com.vip.tycloud.service.teacher;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherProfileRespDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherProfile;

/**
 * 教师与绩效 功能模块 - 教师档案 - 服务接口。
 */
public interface TyHrTeacherProfileService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyHrTeacherProfile getById(Long id);

    TyHrTeacherProfileRespDTO getRespById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyHrTeacherProfile> page(Integer pageNumber, Integer pageSize);

    PageResultDTO<TyHrTeacherProfileRespDTO> pageResp(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyHrTeacherProfile entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyHrTeacherProfile entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


