package com.zhengtoon.config.loaded.plugin.commons.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * systemConfig.properties配置文件加载类
 */
@Configuration
@Data
public class SystemConfig {

    //项目域名
    @Value("${project.domainUrl}")
    private String domainUrl;

    //fastdfs上传文件地址
    @Value("${project.fastdfs.uploadMuiltipart}")
    private String uploadMuiltipart;

    //集成工作台登陆相关配置 - start
    @Value("${oauth.portal}")
    private String oauthPortal;

    @Value("${oauth.imgUrl}")
    private String oauthImgUrl;

    @Value("${oauth.sessionKey}")
    private String sessionKey;

    @Value("${oauth.sessionTimeOut:30}")
    private Long oauthSessionTimeOut;

    @Value("${oauth.uumGetTokenUrl}")
    private String uumGetTokenUrl;

    @Value("${oauth.open.getUserInfo}")
    private String getUserInfo;
    //集成工作台登陆相关配置 - end

    @Value("${thirdPartyCompanyJson}")
    private String thirdPartyCompanyJson;

    @Value("${postalCodeUrl}")
    private String postalCodeUrl;

    @Value("${postageUrl}")
    private String postageUrl;
}
