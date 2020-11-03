package com.zhengtoon.config.loaded.plugin.interceptor;

import com.alibaba.fastjson.JSON;
import com.zhengtoon.config.loaded.plugin.bean.vo.oauth.OauthUserInfo;
import com.zhengtoon.config.loaded.plugin.commons.utils.PcSessionUtils;
import com.zhengtoon.config.loaded.plugin.service.PcSessionService;
import com.zhengtoon.framework.entity.ResponseResult;
import com.zhengtoon.framework.exception.ExceptionCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@NoArgsConstructor
public class PcSessionInterceptor extends HandlerInterceptorAdapter {

    private PcSessionService pcSessionService;

    public PcSessionInterceptor(PcSessionService pcSessionService) {
        this.pcSessionService = pcSessionService;
    }

    /**
     * 在请求处理之前进行调用 获取用户信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        String sessionId= request.getHeader("sessionId");
        log.debug("拦截请求url = {} ,会话的sessionId:{}",request.getRequestURI() ,sessionId);
        if(StringUtils.isBlank(sessionId)){
            return this.returnFailResponse(response);
        }
        OauthUserInfo oauthUserInfo = pcSessionService.getOauthUserInfoBySessionId(sessionId);
        if(oauthUserInfo == null){
            return this.returnFailResponse(response);
        }
        PcSessionUtils.setUserInfo(oauthUserInfo);
        return true;
    }

    /**
     * 返回失败的信息
     * @param response 返回对象
     * @return false
     * @throws IOException IOException
     */
    private boolean returnFailResponse(HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSON(new ResponseResult<>(ExceptionCode.TOKEN_EXPIRE)));
        return false;
    }

}
