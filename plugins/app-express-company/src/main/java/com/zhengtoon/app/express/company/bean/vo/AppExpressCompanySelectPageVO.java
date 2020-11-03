/**
 * 
 */
package com.zhengtoon.app.express.company.bean.vo;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 插件EntityVO
 * @author admin
 * @version 1.0
 * Copyright: Copyright (c) 2017
 * Company:北京政通科技有限公司
 */
 @Data
public class AppExpressCompanySelectPageVO implements Serializable {

	private static final long serialVersionUID = 1L;

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
}