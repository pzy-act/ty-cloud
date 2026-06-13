package com.vip.tycloud.service.artwork;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtArtwork;

/**
 * 陶艺作品管理 功能模块 - 作品建档 - 服务接口。
 */
public interface TyArtArtworkService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyArtArtwork getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyArtArtwork> page(Integer pageNumber, Integer pageSize, String keyword, Integer status);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyArtArtwork entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyArtArtwork entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


