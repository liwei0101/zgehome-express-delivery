/**
 * 
 */
package com.zhengtoon.common.sys.plugin.bean.vo;
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
public class AppProjectInitVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "domainUrl"  ,value =" 域名" )
	private String domainUrl;
	@ApiModelProperty(name = "appProjectInitDictListVOList"  ,value =" 字典集合" )
	private List<AppProjectInitDictListVO> appProjectInitDictListVOList;
	@ApiModelProperty(name = "uploadMuiltipart"  ,value =" 文件上传地址" )
	private String uploadMuiltipart;
	@ApiModelProperty(name = "thirdPartyCompanyJson"  ,value =" 第三方信息json" )
	private String thirdPartyCompanyJson;
	@ApiModelProperty(name = "postalCodeUrl"  ,value =" 邮编地址" )
	private String postalCodeUrl;
	@ApiModelProperty(name = "postageUrl"  ,value =" 邮资地址" )
	private String postageUrl;
}