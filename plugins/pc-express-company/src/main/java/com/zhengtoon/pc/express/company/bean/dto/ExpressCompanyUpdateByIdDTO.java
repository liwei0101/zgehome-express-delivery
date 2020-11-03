/**
 * 
 */
package com.zhengtoon.pc.express.company.bean.dto;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 插件EntityVO
 * @author admin
 * @version 1.0
 * Copyright: Copyright (c) 2017
 * Company:北京政通科技有限公司
 */
 @Data
 public class ExpressCompanyUpdateByIdDTO {

    @ApiModelProperty(name = "id"  ,value =" 主键" )
    private Long id;
    @ApiModelProperty(name = "name"  ,value =" 公司名称" )
    private String name;
    @ApiModelProperty(name = "address"  ,value =" 公司地址" )
    private String address;
    @ApiModelProperty(name = "telephone"  ,value =" 联系电话" )
    private String telephone;
    @ApiModelProperty(name = "frontCover"  ,value =" 公司封面" )
    private String frontCover;
    @ApiModelProperty(name = "compayIntroduce"  ,value =" 公司介绍" )
    private String compayIntroduce;
    @ApiModelProperty(name = "pubStatus"  ,value =" 状态  --  1-下架, 2-发布" )
    private Integer pubStatus;
}