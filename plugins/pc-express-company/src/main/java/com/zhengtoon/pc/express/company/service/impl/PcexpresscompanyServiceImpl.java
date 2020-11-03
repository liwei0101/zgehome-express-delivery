/**
 *
 */
package com.zhengtoon.pc.express.company.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhengtoon.config.loaded.plugin.bean.vo.oauth.OauthUserInfo;
import com.zhengtoon.config.loaded.plugin.commons.constant.CommonConstants;
import com.zhengtoon.config.loaded.plugin.commons.utils.PcSessionUtils;
import com.zhengtoon.framework.exception.BusinessException;
import com.zhengtoon.framework.exception.CoreExceptionCodes;
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
import com.zhengtoon.zgehome.express.delivery.commons.util.JsonBeanUtils;
import com.zhengtoon.zgehome.express.delivery.entity.ExpressCompany;
import com.zhengtoon.zgehome.express.delivery.service.ExpressCompanyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhengtoon.pc.express.company.service.PcexpresscompanyService;
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
public class PcexpresscompanyServiceImpl implements PcexpresscompanyService{

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ExpressCompanyService expressCompanyService;

    public ExpressCompanyInsertVO expressCompanyInsert(ExpressCompanyInsertDTO expressCompanyInsertDTO) {
		ExpressCompanyInsertVO expressCompanyInsertVO = new ExpressCompanyInsertVO();
		//todo write your code
		//获取用户信息
		OauthUserInfo oauthUserInfo = PcSessionUtils.getUserInfo();
		Long userId = Long.parseLong(oauthUserInfo.getMemberId() + "");
		//获取发布状态
		Integer pubStatus = expressCompanyInsertDTO.getPubStatus();
		if (!CommonConstants.PUB_STATUS_ONE.equals(pubStatus) && !CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态错误,直接返回失败
			throw new BusinessException(CoreExceptionCodes.PARAM_IS_ILLEGAL.getCode(), "发布状态错误");
		}
		//复制
		ExpressCompany expressCompany = (ExpressCompany) JsonBeanUtils.copyProperties(expressCompanyInsertDTO, ExpressCompany.class);
		expressCompany.setCreateBy(userId);
		expressCompany.setUpdateBy(userId);
		//当前时间
		Long currTime = System.currentTimeMillis();
		if (CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态
			expressCompany.setPubTime(currTime);
		}
		expressCompanyService.insert(expressCompany);
		expressCompanyInsertVO.setId(expressCompany.getId());
		return expressCompanyInsertVO;
	}


    public ExpressCompanyUpdateByIdVO expressCompanyUpdateById(ExpressCompanyUpdateByIdDTO expressCompanyUpdateByIdDTO) {
		ExpressCompanyUpdateByIdVO expressCompanyUpdateByIdVO = new ExpressCompanyUpdateByIdVO();
		//todo write your code
		//获取用户信息
		OauthUserInfo oauthUserInfo = PcSessionUtils.getUserInfo();
		Long userId = Long.parseLong(oauthUserInfo.getMemberId() + "");
		//获取发布状态
		Integer pubStatus = expressCompanyUpdateByIdDTO.getPubStatus();
		if (!CommonConstants.PUB_STATUS_ONE.equals(pubStatus) && !CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态错误,直接返回失败
			throw new BusinessException(CoreExceptionCodes.PARAM_IS_ILLEGAL.getCode(), "发布状态错误");
		}
		//复制
		ExpressCompany expressCompany = (ExpressCompany) JsonBeanUtils.copyProperties(expressCompanyUpdateByIdDTO, ExpressCompany.class);
		expressCompany.setUpdateBy(userId);
		//当前时间
		Long currTime = System.currentTimeMillis();
		if (CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态
			expressCompany.setPubTime(currTime);
		} else {
			expressCompany.setPubTime(0L);
		}
		expressCompanyUpdateByIdVO.setResult(expressCompanyService.updateById(expressCompany));
		return expressCompanyUpdateByIdVO;
	}


    public Page<ExpressCompanySelectPageVO> expressCompanySelectPage(ExpressCompanySelectPageDTO expressCompanySelectPageDTO) {
		//todo write your code
		Integer pageNo = expressCompanySelectPageDTO.getPageNo();
		Integer pageSize = expressCompanySelectPageDTO.getPageSize();
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
		if (StringUtils.isNotBlank(expressCompanySelectPageDTO.getName())) {
			expressCompanyWrapper.like("name", expressCompanySelectPageDTO.getName());
		}
		expressCompanyWrapper.orderBy("pub_status", false);
		expressCompanyWrapper.orderBy("update_time", false);
		expressCompanyPage = expressCompanyService.selectPage(expressCompanyPage, expressCompanyWrapper);
		//创建返回对象
		Page<ExpressCompanySelectPageVO> expressCompanySelectPageVOPage = (Page)JsonBeanUtils.copyProperties(expressCompanyPage, Page.class);
		//获取分页查询的数据
		List<ExpressCompany> expressCompanyList = expressCompanyPage.getRecords();
		if (CollectionUtils.isNotEmpty(expressCompanyList)) {
			expressCompanySelectPageVOPage.setRecords(JsonBeanUtils.copyList(expressCompanyList, ExpressCompanySelectPageVO.class));
		} else {
			expressCompanySelectPageVOPage.setRecords(new ArrayList<>());
		}
		return expressCompanySelectPageVOPage;
	}


    public ExpressCompanySelectByIdVO expressCompanySelectById(ExpressCompanySelectByIdDTO expressCompanySelectByIdDTO) {
		//todo write your code
		ExpressCompany expressCompany = expressCompanyService.selectById(expressCompanySelectByIdDTO.getId());
		if (null == expressCompany) {
			//对象不存在
			throw new BusinessException(CoreExceptionCodes.CACHE_BEAN_FAIL.getCode(), "内容不存在");
		}
		//复制
		ExpressCompanySelectByIdVO expressCompanySelectByIdVO = (ExpressCompanySelectByIdVO) JsonBeanUtils.copyProperties(expressCompany, ExpressCompanySelectByIdVO.class);
		return expressCompanySelectByIdVO;
	}


    public ExpressCompanyUpdateForPublishVO expressCompanyUpdateForPublish(ExpressCompanyUpdateForPublishDTO expressCompanyUpdateForPublishDTO) {
		ExpressCompanyUpdateForPublishVO expressCompanyUpdateForPublishVO = new ExpressCompanyUpdateForPublishVO();
		//todo write your code
		//获取用户信息
		OauthUserInfo oauthUserInfo = PcSessionUtils.getUserInfo();
		Long userId = Long.parseLong(oauthUserInfo.getMemberId() + "");
		//获取发布状态
		Integer pubStatus = expressCompanyUpdateForPublishDTO.getPubStatus();
		if (!CommonConstants.PUB_STATUS_ONE.equals(pubStatus) && !CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态错误,直接返回失败
			throw new BusinessException(CoreExceptionCodes.PARAM_IS_ILLEGAL.getCode(), "发布状态错误");
		}
		//复制
		ExpressCompany expressCompany = (ExpressCompany) JsonBeanUtils.copyProperties(expressCompanyUpdateForPublishDTO, ExpressCompany.class);
		expressCompany.setUpdateBy(userId);
		//当前时间
		Long currTime = System.currentTimeMillis();
		if (CommonConstants.PUB_STATUS_TWO.equals(pubStatus)) {
			//发布状态
			expressCompany.setPubTime(currTime);
		} else {
			expressCompany.setPubTime(0L);
		}
		expressCompanyUpdateForPublishVO.setResult(expressCompanyService.updateById(expressCompany));
		return expressCompanyUpdateForPublishVO;
	}


    public ExpressCompanyDeleteByIdVO expressCompanyDeleteById(ExpressCompanyDeleteByIdDTO expressCompanyDeleteByIdDTO) {
		ExpressCompanyDeleteByIdVO expressCompanyDeleteByIdVO = new ExpressCompanyDeleteByIdVO();
		//todo write your code
		expressCompanyDeleteByIdVO.setResult(expressCompanyService.deleteById(expressCompanyDeleteByIdDTO.getId()));
		return expressCompanyDeleteByIdVO;
	}


}