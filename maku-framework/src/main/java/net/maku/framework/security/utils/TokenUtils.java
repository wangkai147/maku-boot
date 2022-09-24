package net.maku.framework.security.utils;

import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Token 工具类
 * <p>
 * 1. 生成token
 * 2. 获取token
 *
 * @author wangkai
 */
public class TokenUtils {

    /**
     * 生成 AccessToken
     */
    public static String generator() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 获取 AccessToken
     */
    public static String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(accessToken)) {
            accessToken = request.getParameter("access_token");
        }

        return accessToken;
    }
}
