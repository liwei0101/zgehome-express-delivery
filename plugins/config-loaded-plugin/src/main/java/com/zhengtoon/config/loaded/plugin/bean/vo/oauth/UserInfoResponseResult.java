package com.zhengtoon.config.loaded.plugin.bean.vo.oauth;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResponseResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    /**
     * 返回的数据
     */
    private OauthUserInfo data;// 数据
}
