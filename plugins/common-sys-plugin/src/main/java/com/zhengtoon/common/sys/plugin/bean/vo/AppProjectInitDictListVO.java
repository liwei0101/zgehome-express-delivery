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
public class AppProjectInitDictListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "dictTypeCode"  ,value =" 字典项类型编码" )
	private String dictTypeCode;
	@ApiModelProperty(name = "appProjectInitZtDictItemListVOList"  ,value =" 字典项集合" )
	private List<AppProjectInitZtDictItemListVO> appProjectInitZtDictItemListVOList;
}