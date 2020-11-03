/**
 *
 */
package com.zhengtoon.app.express.company.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectPageVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectPageDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectListVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectListDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectByIdVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectByIdDTO;

import java.util.List;


/**
 * 插件Service接口
 *
 * @author admin
 * @version 1.0
 * Copyright: Copyright (c) 2018
 * Company:北京政通科技有限公司
 */
public interface AppexpresscompanyService {

	/**
	 * 快递公司插件app-分页查询快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	Page<AppExpressCompanySelectPageVO> appExpressCompanySelectPage(AppExpressCompanySelectPageDTO appExpressCompanySelectPageDTO);
	/**
	 * 快递公司插件app-查询快递公司信息列表
	 *
	 *
	 * @return  boolean
	 */
	List<AppExpressCompanySelectListVO> appExpressCompanySelectList(AppExpressCompanySelectListDTO appExpressCompanySelectListDTO);
	/**
	 * 快递公司插件app-查询快递公司信息详情
	 *
	 *
	 * @return  boolean
	 */
	AppExpressCompanySelectByIdVO appExpressCompanySelectById(AppExpressCompanySelectByIdDTO appExpressCompanySelectByIdDTO);

}