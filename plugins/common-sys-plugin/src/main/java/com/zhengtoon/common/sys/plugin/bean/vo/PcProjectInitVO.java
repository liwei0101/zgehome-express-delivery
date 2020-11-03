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
public class PcProjectInitVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "domainUrl"  ,value =" 域名" )
	private String domainUrl;
	@ApiModelProperty(name = "uploadMuiltipart"  ,value =" 文件上传地址" )
	private String uploadMuiltipart;
}