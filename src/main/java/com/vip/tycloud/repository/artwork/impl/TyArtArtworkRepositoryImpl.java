package com.vip.tycloud.repository.artwork.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.artwork.TyArtArtwork;
import com.vip.tycloud.mapper.artwork.TyArtArtworkMapper;
import com.vip.tycloud.repository.artwork.TyArtArtworkRepository;
import org.springframework.stereotype.Repository;

/**
 * 陶艺作品管理 功能模块 - 作品建档 - 仓储实现类。
 */
@Repository
public class TyArtArtworkRepositoryImpl extends BaseRepositoryImpl<TyArtArtworkMapper, TyArtArtwork> implements TyArtArtworkRepository {

    public TyArtArtworkRepositoryImpl(TyArtArtworkMapper baseMapper) {
        super(baseMapper);
    }
}


