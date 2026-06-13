package com.vip.tycloud.service.student;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuStudentGuardian;
import java.util.List;

/**
 * 学员管理 功能模块 - 学员监护关系 - 服务接口。
 */
public interface TyStuStudentGuardianService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyStuStudentGuardian getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyStuStudentGuardian> page(Integer pageNumber, Integer pageSize);

    /**
     * Query guardian relations by student ID.
     *
     * @param studentId student ID
     * @return relation list
     */
    List<TyStuStudentGuardian> listByStudentId(Long studentId);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyStuStudentGuardian entity);

    /**
     * Bind guardian to student.
     *
     * @param entity relation entity
     * @return whether success
     */
    boolean bind(TyStuStudentGuardian entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyStuStudentGuardian entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


