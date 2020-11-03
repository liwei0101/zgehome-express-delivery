/**
 * @author admin
 */
package com.zhengtoon.pc.express.company.web.controller;

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

import com.zhengtoon.pc.express.company.service.PcexpresscompanyService;
import com.zhengtoon.pc.express.company.commons.exception.ExceptionCode;
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
@Api(tags = "插件服务-pc快递公司")
@Slf4j
@RequestMapping(value = "/pc/expressCompany")
public class PcexpresscompanyController{

	@Autowired
	private PcexpresscompanyService pcexpresscompanyService;

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public ResponseResult expressCompanyInsert(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanyInsertDTO pcexpresscompany) {

		if(pcexpresscompany.getName() == null ||  "".equals( pcexpresscompany.getName() )){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "公司名称不能为空");
		}
		if(pcexpresscompany.getAddress() == null ||  "".equals( pcexpresscompany.getAddress() )){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "公司地址不能为空");
		}
		if(pcexpresscompany.getTelephone() == null ||  "".equals( pcexpresscompany.getTelephone() )){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "联系电话不能为空");
		}
		if(pcexpresscompany.getFrontCover() == null ||  "".equals( pcexpresscompany.getFrontCover() )){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "公司封面不能为空");
		}
		if(pcexpresscompany.getCompayIntroduce() == null ||  "".equals( pcexpresscompany.getCompayIntroduce() )){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "公司介绍不能为空");
		}
		if(pcexpresscompany.getPubStatus() == null ){
			return ResponseResult.fail(CoreExceptionCodes.PARAM_IS_NULL.getCode(), "状态  --  1-下架, 2-发布不能为空");
		}
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanyInsert(pcexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/updateById",method = RequestMethod.POST)
	public ResponseResult expressCompanyUpdateById(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanyUpdateByIdDTO pcexpresscompany) {

		if(pcexpresscompany.getId() == null ){
			return ResponseResult.fail(99999, "主键不能为空");
		}
		if(pcexpresscompany.getName() == null ||  "".equals( pcexpresscompany.getName() )){
			return ResponseResult.fail(99999, "公司名称不能为空");
		}
		if(pcexpresscompany.getAddress() == null ||  "".equals( pcexpresscompany.getAddress() )){
			return ResponseResult.fail(99999, "公司地址不能为空");
		}
		if(pcexpresscompany.getTelephone() == null ||  "".equals( pcexpresscompany.getTelephone() )){
			return ResponseResult.fail(99999, "联系电话不能为空");
		}
		if(pcexpresscompany.getFrontCover() == null ||  "".equals( pcexpresscompany.getFrontCover() )){
			return ResponseResult.fail(99999, "公司封面不能为空");
		}
		if(pcexpresscompany.getCompayIntroduce() == null ||  "".equals( pcexpresscompany.getCompayIntroduce() )){
			return ResponseResult.fail(99999, "公司介绍不能为空");
		}
		if(pcexpresscompany.getPubStatus() == null ){
			return ResponseResult.fail(99999, "状态  --  1-下架, 2-发布不能为空");
		}
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanyUpdateById(pcexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/selectPage",method = RequestMethod.POST)
	public ResponseResult expressCompanySelectPage(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanySelectPageDTO pcexpresscompany) {
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanySelectPage(pcexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/selectById",method = RequestMethod.POST)
	public ResponseResult expressCompanySelectById(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanySelectByIdDTO pcexpresscompany) {

		if(pcexpresscompany.getId() == null ){
			return ResponseResult.fail(99999, "主键不能为空");
		}
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanySelectById(pcexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/updateForPublish",method = RequestMethod.POST)
	public ResponseResult expressCompanyUpdateForPublish(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanyUpdateForPublishDTO pcexpresscompany) {

		if(pcexpresscompany.getId() == null ){
			return ResponseResult.fail(99999, "主键不能为空");
		}
		if(pcexpresscompany.getPubStatus() == null ){
			return ResponseResult.fail(99999, "状态  --  1-下架, 2-发布不能为空");
		}
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanyUpdateForPublish(pcexpresscompany));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Pcexpresscompany ", httpMethod = "POST", notes = "查询Pcexpresscompany")
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST)
	public ResponseResult expressCompanyDeleteById(
			@ApiParam(required = true, name = "pcexpresscompany", value = "查询Pcexpresscompany参数")@RequestBody final ExpressCompanyDeleteByIdDTO pcexpresscompany) {

		if(pcexpresscompany.getId() == null ){
			return ResponseResult.fail(99999, "主键不能为空");
		}
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(pcexpresscompanyService.expressCompanyDeleteById(pcexpresscompany));
			}
		}.sendRequest();
	}


}