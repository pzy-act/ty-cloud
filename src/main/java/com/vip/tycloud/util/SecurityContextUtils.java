package com.vip.tycloud.util;

import com.vip.tycloud.security.LoginUser;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security context helper.
 */
public final class SecurityContextUtils {

    private SecurityContextUtils() {
    }

    public static Long getCurrentUserId() {
        LoginUser loginUser = getCurrentLoginUser();
        return Objects.isNull(loginUser) ? null : loginUser.getUserId();
    }

    public static Long getCurrentCampusId() {
        LoginUser loginUser = getCurrentLoginUser();
        return Objects.isNull(loginUser) ? null : loginUser.getCampusId();
    }

    private static LoginUser getCurrentLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return principal instanceof LoginUser ? (LoginUser) principal : null;
    }
}
