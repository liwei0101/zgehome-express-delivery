/**
 *
 */
package com.zhengtoon.pc.express.company.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanyInsertVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanyInsertDTO;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanyUpdateByIdVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanyUpdateByIdDTO;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanySelectPageVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanySelectPageDTO;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanySelectByIdVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanySelectByIdDTO;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanyUpdateForPublishVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanyUpdateForPublishDTO;
import com.zhengtoon.pc.express.company.bean.vo.ExpressCompanyDeleteByIdVO;
import com.zhengtoon.pc.express.company.bean.dto.ExpressCompanyDeleteByIdDTO;


/**
 * 插件Service接口
 *
 * @author admin
 * @version 1.0
 * Copyright: Copyright (c) 2018
 * Company:北京政通科技有限公司
 */
public interface PcexpresscompanyService {

	/**
	 * 快递公司插件pc-保存快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	ExpressCompanyInsertVO expressCompanyInsert(ExpressCompanyInsertDTO expressCompanyInsertDTO);
	/**
	 * 快递公司插件pc-更新快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	ExpressCompanyUpdateByIdVO expressCompanyUpdateById(ExpressCompanyUpdateByIdDTO expressCompanyUpdateByIdDTO);
	/**
	 * 插件模块pc-分页查询快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	Page<ExpressCompanySelectPageVO> expressCompanySelectPage(ExpressCompanySelectPageDTO expressCompanySelectPageDTO);
	/**
	 * 快递公司插件pc-查询快递公司信息详情
	 *
	 *
	 * @return  boolean
	 */
	ExpressCompanySelectByIdVO expressCompanySelectById(ExpressCompanySelectByIdDTO expressCompanySelectByIdDTO);
	/**
	 * 快递公司插件pc-发布快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	ExpressCompanyUpdateForPublishVO expressCompanyUpdateForPublish(ExpressCompanyUpdateForPublishDTO expressCompanyUpdateForPublishDTO);
	/**
	 * 快递公司插件pc-删除快递公司信息
	 *
	 *
	 * @return  boolean
	 */
	ExpressCompanyDeleteByIdVO expressCompanyDeleteById(ExpressCompanyDeleteByIdDTO expressCompanyDeleteByIdDTO);

}