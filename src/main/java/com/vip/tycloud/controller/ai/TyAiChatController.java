package com.vip.tycloud.controller.ai;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.ai.TyAiChatMessageReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatMessageRespDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionCreateReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionRespDTO;
import com.vip.tycloud.dto.ai.TyAiModelRespDTO;
import com.vip.tycloud.dto.ai.TyAiQuotaRespDTO;
import com.vip.tycloud.dto.ai.TyAiSendMessageRespDTO;
import com.vip.tycloud.security.LoginUser;
import com.vip.tycloud.service.ai.TyAiChatService;
import com.vip.tycloud.service.ai.TyAiQuotaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 智能问答 功能模块 - 聊天控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/chat")
@PreAuthorize("hasRole('ADMIN')")
public class TyAiChatController {

    private final TyAiChatService tyAiChatService;

    private final TyAiQuotaService tyAiQuotaService;

    @PostMapping("/session/create")
    public ApiResponse<TyAiChatSessionRespDTO> createSession(@Valid @RequestBody TyAiChatSessionCreateReqDTO req) {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(tyAiChatService.createSession(req, loginUser.getUserId(), loginUser.getCampusId()));
    }

    @PostMapping("/session/page")
    public ApiResponse<PageResultDTO<TyAiChatSessionRespDTO>> pageSession(@Valid @RequestBody PageQueryReqDTO req) {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(tyAiChatService.pageSession(loginUser.getUserId(), req.getPageNumber(), req.getPageSize()));
    }

    @GetMapping("/message/list/{sessionId}")
    public ApiResponse<List<TyAiChatMessageRespDTO>> listMessages(@PathVariable Long sessionId) {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(tyAiChatService.listMessages(loginUser.getUserId(), sessionId));
    }

    @PostMapping("/message/send")
    public ApiResponse<TyAiSendMessageRespDTO> sendMessage(@Valid @RequestBody TyAiChatMessageReqDTO req) {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(tyAiChatService.sendMessage(req, loginUser.getUserId(), loginUser.getCampusId()));
    }

    @DeleteMapping("/session/{sessionId}")
    public ApiResponse<Boolean> deleteSession(@PathVariable Long sessionId) {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        return ApiResponse.success(tyAiChatService.deleteSession(loginUser.getUserId(), sessionId, loginUser.getUserId()));
    }

    @GetMapping("/quota/me")
    public ApiResponse<List<TyAiQuotaRespDTO>> myQuota() {
        LoginUser loginUser = currentUser();
        if (Objects.isNull(loginUser)) {
            return ApiResponse.fail("未登录或登录已过期");
        }
        tyAiQuotaService.ensureDefaultQuota(loginUser.getUserId(), loginUser.getUserId());
        return ApiResponse.success(tyAiQuotaService.getMyQuota(loginUser.getUserId()));
    }

    @GetMapping("/model/list")
    public ApiResponse<List<TyAiModelRespDTO>> listModels() {
        return ApiResponse.success(tyAiChatService.listModels());
    }

    private LoginUser currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) principal;
        }
        return null;
    }
}
