package com.zhengtoon.config.loaded.plugin.bean.vo.oauth;

import lombok.Data;

import java.io.Serializable;

/**
 * AccessToken类
 */
@Data
public class AccessTokenVO implements Serializable {
    private String accessToken;
    private String gsessionId;
    private String refreshToken;
    private Integer refreshTokenExpireIn;
    private String scope;
    private Integer tokenExpireIn;
}
