package com.vip.tycloud.service.student;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudent;

/**
 * 学员管理 功能模块 - 学员档案 - 服务接口。
 */
public interface TyStuStudentService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyStuStudent getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyStuStudent> page(Integer pageNumber, Integer pageSize);

    /**
     * Filtered page query.
     *
     * @param pageNumber page number
     * @param pageSize page size
     * @param keyword keyword
     * @param status student status
     * @param tagId tag id
     * @return page result
     */
    PageResultDTO<TyStuStudent> page(Integer pageNumber, Integer pageSize, String keyword, Integer status, Long tagId);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyStuStudent entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyStuStudent entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


