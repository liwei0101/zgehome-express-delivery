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
 public class ExpressCompanyDeleteByIdDTO {

    @ApiModelProperty(name = "id"  ,value =" 主键" )
    private Long id;
}