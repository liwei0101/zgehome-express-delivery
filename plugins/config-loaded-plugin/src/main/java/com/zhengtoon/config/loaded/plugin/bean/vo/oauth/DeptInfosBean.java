package com.zhengtoon.config.loaded.plugin.bean.vo.oauth;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门信息
 */
@Data
public class DeptInfosBean implements Serializable {

    private String deptCode;
    private int deptId;
    private String deptName;
    private String deptNodes;
    private PositionInfoBean positionInfo;
    private int status;

}
