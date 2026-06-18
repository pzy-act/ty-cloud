package com.vip.tycloud.repository.ai;

import com.vip.tycloud.common.repository.BaseRepository;
import com.vip.tycloud.entity.ai.TyAiUserQuota;
import java.time.LocalDate;
import java.util.List;

/**
 * 智能问答 功能模块 - 用户额度 - 仓储接口。
 */
public interface TyAiUserQuotaRepository extends BaseRepository<TyAiUserQuota> {

    List<TyAiUserQuota> listCurrentByUserId(Long userId, LocalDate today);

    TyAiUserQuota getByUserIdAndPeriod(Long userId, String quotaPeriod, LocalDate periodStart, LocalDate periodEnd);

    int increaseUsage(Long id, Integer questionCount, Integer tokenCount, Long operatorId);
}
