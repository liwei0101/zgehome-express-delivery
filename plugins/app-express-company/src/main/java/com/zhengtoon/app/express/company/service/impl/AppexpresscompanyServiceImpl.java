/**
 *
 */
package com.zhengtoon.app.express.company.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectPageVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectPageDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectListVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectListDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectByIdVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectByIdDTO;


import com.zhengtoon.config.loaded.plugin.commons.constant.CommonConstants;
import com.zhengtoon.framework.exception.BusinessException;
import com.zhengtoon.framework.exception.CoreExceptionCodes;
import com.zhengtoon.zgehome.express.delivery.commons.util.JsonBeanUtils;
import com.zhengtoon.zgehome.express.delivery.entity.ExpressCompany;
import com.zhengtoon.zgehome.express.delivery.service.ExpressCompanyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhengtoon.app.express.company.service.AppexpresscompanyService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * 插件 impl实现
 *
 * @author admin
 * @since 1.0
 * Copyright: Copyright (c) 2017
 * Company:北京政通科技有限公司
 */
@Service
@Slf4j
public class AppexpresscompanyServiceImpl implements AppexpresscompanyService{

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ExpressCompanyService expressCompanyService;

    public Page<AppExpressCompanySelectPageVO> appExpressCompanySelectPage(AppExpressCompanySelectPageDTO appExpressCompanySelectPageDTO) {
		//todo write your code
		Integer pageNo = appExpressCompanySelectPageDTO.getPageNo();
		Integer pageSize = appExpressCompanySelectPageDTO.getPageSize();
		if (null == pageNo) {
			pageNo = CommonConstants.PAGE_NO;
		}
		if (null == pageSize) {
			pageSize = CommonConstants.PAGE_SIZE;
		}
		//设置分页条件
		Page<ExpressCompany> expressCompanyPage = new Page(pageNo, pageSize);
		//设置查询条件
		Wrapper<ExpressCompany> expressCompanyWrapper = Condition.wrapper();
		if (StringUtils.isNotBlank(appExpressCompanySelectPageDTO.getName())) {
			expressCompanyWrapper.like("name", appExpressCompanySelectPageDTO.getName());
		}
		expressCompanyWrapper.eq("pub_status", CommonConstants.PUB_STATUS_TWO);
		expressCompanyWrapper.orderBy("pub_time", false);
		expressCompanyPage = expressCompanyService.selectPage(expressCompanyPage, expressCompanyWrapper);
		//创建返回对象
		Page<AppExpressCompanySelectPageVO> appExpressCompanySelectPageVOPage = (Page) JsonBeanUtils.copyProperties(expressCompanyPage, Page.class);
		//获取分页查询的数据
		List<ExpressCompany> expressCompanyList = expressCompanyPage.getRecords();
		if (CollectionUtils.isNotEmpty(expressCompanyList)) {
			appExpressCompanySelectPageVOPage.setRecords(JsonBeanUtils.copyList(expressCompanyList, AppExpressCompanySelectPageVO.class));
		} else {
			appExpressCompanySelectPageVOPage.setRecords(new ArrayList<>());
		}
		return appExpressCompanySelectPageVOPage;
	}


    public List<AppExpressCompanySelectListVO> appExpressCompanySelectList(AppExpressCompanySelectListDTO appExpressCompanySelectListDTO) {
		//todo write your code
		//设置查询条件
		Wrapper<ExpressCompany> expressCompanyWrapper = Condition.wrapper();
		if (StringUtils.isNotBlank(appExpressCompanySelectListDTO.getName())) {
			expressCompanyWrapper.like("name", appExpressCompanySelectListDTO.getName());
		}
		List<ExpressCompany> expressCompanyList = expressCompanyService.selectList(expressCompanyWrapper);
		if (CollectionUtils.isNotEmpty(expressCompanyList)) {
			return JsonBeanUtils.copyList(expressCompanyList, AppExpressCompanySelectListVO.class);
		} else {
			return new ArrayList<>();
		}
	}


    public AppExpressCompanySelectByIdVO appExpressCompanySelectById(AppExpressCompanySelectByIdDTO appExpressCompanySelectByIdDTO) {
		//todo write your code
		ExpressCompany expressCompany = expressCompanyService.selectById(appExpressCompanySelectByIdDTO.getId());
		if (null == expressCompany) {
			//对象不存在
			throw new BusinessException(CoreExceptionCodes.CACHE_BEAN_FAIL.getCode(), "内容不存在");
		}
		//复制
		AppExpressCompanySelectByIdVO appExpressCompanySelectByIdVO = (AppExpressCompanySelectByIdVO) JsonBeanUtils.copyProperties(expressCompany, AppExpressCompanySelectByIdVO.class);
		return appExpressCompanySelectByIdVO;
	}


}