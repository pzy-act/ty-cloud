package com.vip.tycloud.service.contract;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.contract.TyBizContractItem;

/**
 * 报名与合同 功能模块 - 合同明细 - 服务接口。
 */
public interface TyBizContractItemService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyBizContractItem getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyBizContractItem> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyBizContractItem entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyBizContractItem entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


