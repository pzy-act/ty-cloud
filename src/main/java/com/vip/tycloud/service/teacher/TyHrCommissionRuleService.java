package com.vip.tycloud.service.teacher;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.teacher.TyHrCommissionRule;

/**
 * 教师与绩效 功能模块 - 提成规则 - 服务接口。
 */
public interface TyHrCommissionRuleService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyHrCommissionRule getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyHrCommissionRule> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyHrCommissionRule entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyHrCommissionRule entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


