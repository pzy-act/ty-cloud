package com.vip.tycloud.security;

import com.vip.tycloud.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 认证授权 - JWT 令牌处理器。
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String TOKEN_TYPE_CLAIM = "typ";

    private static final String TOKEN_TYPE_ACCESS = "access";

    private static final String TOKEN_TYPE_REFRESH = "refresh";

    private static final String USER_ID_CLAIM = "uid";

    private static final String CAMPUS_ID_CLAIM = "cid";

    private static final String REMEMBER_ME_CLAIM = "rmb";

    private final JwtProperties jwtProperties;

    /**
     * 生成访问令牌。
     *
     * @param loginUser 登录用户
     * @return 访问令牌
     */
    public String createAccessToken(LoginUser loginUser) {
        return createToken(loginUser, TOKEN_TYPE_ACCESS, jwtProperties.getAccessTokenExpireSeconds(), false);
    }

    /**
     * 生成刷新令牌。
     *
     * @param loginUser 登录用户
     * @param rememberMe 是否 7 天免登录
     * @return 刷新令牌
     */
    public String createRefreshToken(LoginUser loginUser, boolean rememberMe) {
        long expireSeconds = rememberMe
            ? jwtProperties.getRememberMeRefreshTokenExpireSeconds()
            : jwtProperties.getRefreshTokenExpireSeconds();
        return createToken(loginUser, TOKEN_TYPE_REFRESH, expireSeconds, rememberMe);
    }

    /**
     * 校验令牌是否合法。
     *
     * @param token JWT
     * @return 是否合法
     */
    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * 判断是否访问令牌。
     *
     * @param token JWT
     * @return 判断结果
     */
    public boolean isAccessToken(String token) {
        String tokenType = parseClaims(token).get(TOKEN_TYPE_CLAIM, String.class);
        return TOKEN_TYPE_ACCESS.equals(tokenType);
    }

    /**
     * 判断是否刷新令牌。
     *
     * @param token JWT
     * @return 判断结果
     */
    public boolean isRefreshToken(String token) {
        String tokenType = parseClaims(token).get(TOKEN_TYPE_CLAIM, String.class);
        return TOKEN_TYPE_REFRESH.equals(tokenType);
    }

    /**
     * 获取令牌中的用户名。
     *
     * @param token JWT
     * @return 用户名
     */
    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * 获取令牌中的用户ID。
     *
     * @param token JWT
     * @return 用户ID
     */
    public Long getUserId(String token) {
        Object userId = parseClaims(token).get(USER_ID_CLAIM);
        if (Objects.isNull(userId)) {
            return null;
        }
        return Long.valueOf(userId.toString());
    }

    /**
     * 获取令牌中的校区ID。
     *
     * @param token JWT
     * @return 校区ID
     */
    public Long getCampusId(String token) {
        Object campusId = parseClaims(token).get(CAMPUS_ID_CLAIM);
        if (Objects.isNull(campusId)) {
            return null;
        }
        return Long.valueOf(campusId.toString());
    }

    /**
     * 判断刷新令牌是否 7 天免登录。
     *
     * @param token JWT
     * @return 判断结果
     */
    public boolean isRememberMe(String token) {
        Boolean rememberMe = parseClaims(token).get(REMEMBER_ME_CLAIM, Boolean.class);
        return Boolean.TRUE.equals(rememberMe);
    }

    /**
     * 获取访问令牌有效期（秒）。
     *
     * @return 秒数
     */
    public long getAccessTokenExpireSeconds() {
        return jwtProperties.getAccessTokenExpireSeconds();
    }

    /**
     * 获取刷新令牌有效期（秒）。
     *
     * @param rememberMe 是否 7 天免登录
     * @return 秒数
     */
    public long getRefreshTokenExpireSeconds(boolean rememberMe) {
        return rememberMe
            ? jwtProperties.getRememberMeRefreshTokenExpireSeconds()
            : jwtProperties.getRefreshTokenExpireSeconds();
    }

    private String createToken(LoginUser loginUser, String tokenType, long expireSeconds, boolean rememberMe) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expireSeconds * 1000);

        return Jwts.builder()
            .subject(loginUser.getUsername())
            .claim(USER_ID_CLAIM, loginUser.getUserId())
            .claim(CAMPUS_ID_CLAIM, loginUser.getCampusId())
            .claim(TOKEN_TYPE_CLAIM, tokenType)
            .claim(REMEMBER_ME_CLAIM, rememberMe)
            .issuedAt(now)
            .expiration(expireTime)
            .signWith(getSecretKey())
            .compact();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
