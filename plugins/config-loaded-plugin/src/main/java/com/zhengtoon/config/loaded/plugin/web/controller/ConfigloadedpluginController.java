/**
 * @author admin
 */
package com.zhengtoon.config.loaded.plugin.web.controller;


import com.zhengtoon.config.loaded.plugin.service.ConfigloadedpluginService;
import com.zhengtoon.config.loaded.plugin.commons.exception.ExceptionCode;
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
@Api(tags = "插件服务")
@Slf4j
public class ConfigloadedpluginController{

	@Autowired
	private ConfigloadedpluginService configloadedpluginService;


}