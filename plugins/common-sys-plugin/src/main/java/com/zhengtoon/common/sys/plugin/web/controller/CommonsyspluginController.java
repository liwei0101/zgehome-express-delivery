/**
 * @author admin
 */
package com.zhengtoon.common.sys.plugin.web.controller;

import com.zhengtoon.common.sys.plugin.bean.vo.AppProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcUploadFileDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.AppUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppUploadFileDTO;

import com.zhengtoon.common.sys.plugin.service.CommonsyspluginService;
import com.zhengtoon.common.sys.plugin.commons.exception.ExceptionCode;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "插件服务-公共服务")
@Slf4j
public class CommonsyspluginController{

	@Autowired
	private CommonsyspluginService commonsyspluginService;

	@ApiOperation(value = "查询Commonsysplugin ", httpMethod = "POST", notes = "查询Commonsysplugin")
	@RequestMapping(value = "/app/appProjectInit",method = RequestMethod.POST)
	public ResponseResult appProjectInit(
			@ApiParam(required = true, name = "commonsysplugin", value = "查询Commonsysplugin参数")@RequestBody final AppProjectInitDTO commonsysplugin) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(commonsyspluginService.appProjectInit(commonsysplugin));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Commonsysplugin ", httpMethod = "POST", notes = "查询Commonsysplugin")
	@RequestMapping(value = "/pc/pcProjectInit",method = RequestMethod.POST)
	public ResponseResult pcProjectInit(
			@ApiParam(required = true, name = "commonsysplugin", value = "查询Commonsysplugin参数")@RequestBody final PcProjectInitDTO commonsysplugin) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(commonsyspluginService.pcProjectInit(commonsysplugin));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Commonsysplugin ", httpMethod = "POST", notes = "查询Commonsysplugin")
	@RequestMapping(value = "/pc/pcUploadFile",method = RequestMethod.POST)
	public ResponseResult pcUploadFile(@RequestParam("multipartFile") final MultipartFile file) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(commonsyspluginService.pcUploadFile(file));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "查询Commonsysplugin ", httpMethod = "POST", notes = "查询Commonsysplugin")
	@RequestMapping(value = "/app/appUploadFile",method = RequestMethod.POST)
	public ResponseResult appUploadFile(@RequestParam("multipartFile") final MultipartFile file) {

		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(commonsyspluginService.appUploadFile(file));
			}
		}.sendRequest();
	}

	@ApiOperation(value = "pc工作台跳转 ", httpMethod = "GET", notes = "pc工作台跳转")
	@RequestMapping(value = "/pc/pcValidateAuthCode", method = RequestMethod.GET)
	public ResponseResult pcValidateAuthCode(final @RequestParam String code) {
		return new WebResCallback() {
			@Override
			public void execute(WebResCriteria criteria, Object... params) {
				criteria.addSingleResult(commonsyspluginService.pcValidateAuthCode(code));
			}
		}.sendRequest();
	}


}