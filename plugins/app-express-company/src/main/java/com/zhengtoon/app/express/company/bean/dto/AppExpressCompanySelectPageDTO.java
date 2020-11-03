/**
 *
 */
package com.zhengtoon.app.express.company.bean.dto;
import java.util.List;

import com.zhengtoon.zgehome.express.delivery.commons.util.PageVo;
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
public class AppExpressCompanySelectPageDTO extends PageVo {

    @ApiModelProperty(name = "name"  ,value =" 公司名称" )
    private String name;
}