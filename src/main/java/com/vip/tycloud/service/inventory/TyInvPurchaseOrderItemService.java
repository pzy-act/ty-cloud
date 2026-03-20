package com.vip.tycloud.service.inventory;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrderItem;

/**
 * 耗材与库存 功能模块 - 采购明细 - 服务接口。
 */
public interface TyInvPurchaseOrderItemService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyInvPurchaseOrderItem getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyInvPurchaseOrderItem> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyInvPurchaseOrderItem entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyInvPurchaseOrderItem entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


