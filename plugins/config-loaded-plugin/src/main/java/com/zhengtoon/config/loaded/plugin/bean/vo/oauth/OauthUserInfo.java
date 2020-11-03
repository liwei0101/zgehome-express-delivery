package com.zhengtoon.config.loaded.plugin.bean.vo.oauth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
@Data
public class OauthUserInfo implements Serializable {

    private boolean appAdmin;
    private String cryptoMail;
    private String email;
    private String extId;
    private String memberCode;
    private int memberId;
    private String memberName;
    private String mobile;
    private int orgId;
    private String orgName;
    private String photo;
    private String remark;
    private String sex;
    private String tmail;
    private List<DeptInfosBean> deptInfos;

}
