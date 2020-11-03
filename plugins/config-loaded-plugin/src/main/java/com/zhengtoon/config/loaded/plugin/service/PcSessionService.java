package com.zhengtoon.config.loaded.plugin.service;

import com.zhengtoon.config.loaded.plugin.bean.vo.oauth.OauthUserInfo;

public interface PcSessionService {

    /**
     * 根据sessionId获取用户信息
     * @param sessionId
     * @return
     */
    OauthUserInfo getOauthUserInfoBySessionId(String sessionId);

    /**
     * 清除redis信息
     * @param sessionId
     */
    void invalidSessionId(String sessionId);

}
