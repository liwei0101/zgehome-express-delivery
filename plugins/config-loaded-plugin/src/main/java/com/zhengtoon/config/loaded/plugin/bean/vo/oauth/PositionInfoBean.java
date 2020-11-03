package com.zhengtoon.config.loaded.plugin.bean.vo.oauth;

import lombok.Data;

import java.io.Serializable;

/**
 * 职位信息
 */
@Data
public class PositionInfoBean implements Serializable {

    private int createTime;
    private int deleteFlag;
    private int orgId;
    private int positionGroupId;
    private int positionId;
    private String positionName;
    private int positionType;
    private int updateTime;

}
