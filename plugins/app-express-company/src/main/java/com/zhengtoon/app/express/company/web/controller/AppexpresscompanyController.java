/**
 * @author admin
 */
package com.zhengtoon.app.express.company.web.controller;

import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectPageVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectPageDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectListVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectListDTO;
import com.zhengtoon.app.express.company.bean.vo.AppExpressCompanySelectByIdVO;
import com.zhengtoon.app.express.company.bean.dto.AppExpressCompanySelectByIdDTO;

import com.zhengtoon.app.express.company.service.AppexpresscompanyService;
import com.zhengtoon.app.express.company.commons.exception.ExceptionCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.*;
import com.zhengtoon.framework.entity.ResponseResult;
import com.zhengtoon.framework.web.common.WebResCallback;
import com.zhengtoon.framework.web.common.WebResCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "插件服务-app快递公司")
@Slf4j
@RequestMapping(value = "/app/expressCompany")
public class AppexpresscompanyController{

	@Autowired
	private AppexpresscompanyService appexpresscompanyService;

	@ApiOperation(value = "查询Appexpresscompany ", httpMethod = "POST", notes = "查询Appexpresscompany")
	@RequestMapping(value = "/selectPage",method = RequestMethod.POST)
	public ResponseResult appExpressCompanySelectPage(
			@ApiParam(required = true, name = "appexpresscompany", value = "查询Appexpresscompany参数")@RequestBody final AppExpressCompanySelectPageDTO appexpresscompany) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(appexpresscompanyService.appExpressCompanySelectPage(appexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Appexpresscompany ", httpMethod = "POST", notes = "查询Appexpresscompany")
	@RequestMapping(value = "/selectList",method = RequestMethod.POST)
	public ResponseResult appExpressCompanySelectList(
			@ApiParam(required = true, name = "appexpresscompany", value = "查询Appexpresscompany参数")@RequestBody final AppExpressCompanySelectListDTO appexpresscompany) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(appexpresscompanyService.appExpressCompanySelectList(appexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Appexpresscompany ", httpMethod = "POST", notes = "查询Appexpresscompany")
	@RequestMapping(value = "/selectById",method = RequestMethod.POST)
	public ResponseResult appExpressCompanySelectById(
			@ApiParam(required = true, name = "appexpresscompany", value = "查询Appexpresscompany参数")@RequestBody final AppExpressCompanySelectByIdDTO appexpresscompany) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(appexpresscompanyService.appExpressCompanySelectById(appexpresscompany));
			}
		}.sendRequest();
	}


}