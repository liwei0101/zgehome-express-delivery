package com.zhengtoon.config.loaded.plugin.commons.utils;

import com.zhengtoon.config.loaded.plugin.bean.vo.oauth.OauthUserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 *  pc用户信息工具类
 */
@Slf4j
public class PcSessionUtils {

    private PcSessionUtils() {
    }

    private static final ThreadLocal<OauthUserInfo> LOCAL = new ThreadLocal<>();

    public static void setUserInfo(OauthUserInfo oauthUserInfo) {
        LOCAL.set(oauthUserInfo);
    }

    public static OauthUserInfo getUserInfo() {
        return LOCAL.get();
    }

    static void deleteUserInfo() {
        if (LOCAL.get() != null) {
            LOCAL.remove();
        }
    }

}
