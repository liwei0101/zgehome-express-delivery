package com.zhengtoon.config.loaded.plugin.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhengtoon.config.loaded.plugin.bean.vo.oauth.OauthUserInfo;
import com.zhengtoon.config.loaded.plugin.commons.config.SystemConfig;
import com.zhengtoon.config.loaded.plugin.service.PcSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PcSessionServiceImpl implements PcSessionService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public OauthUserInfo getOauthUserInfoBySessionId(String sessionId) {
        BoundValueOperations<String, String> boundValueOps = stringRedisTemplate.boundValueOps(systemConfig.getSessionKey() + sessionId);
        if (boundValueOps != null) {
            String userStr = boundValueOps.get();
            //增加续时逻辑
            boundValueOps.expire(systemConfig.getOauthSessionTimeOut(), TimeUnit.MINUTES);
            return JSON.parseObject(userStr, OauthUserInfo.class);
        }
        return null;
    }

    @Override
    public void invalidSessionId(String sessionId) {
        stringRedisTemplate.delete(systemConfig.getSessionKey() + sessionId);
    }
}
