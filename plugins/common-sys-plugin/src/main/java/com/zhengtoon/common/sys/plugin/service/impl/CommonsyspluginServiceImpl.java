/**
 *
 */
package com.zhengtoon.common.sys.plugin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhengtoon.common.sys.plugin.bean.vo.AppProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcUploadFileDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.AppUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppUploadFileDTO;


import com.zhengtoon.config.loaded.plugin.commons.config.SystemConfig;
import com.zhengtoon.config.loaded.plugin.commons.constant.CommonConstants;
import com.zhengtoon.framework.exception.BusinessException;
import com.zhengtoon.framework.exception.CoreExceptionCodes;
import com.zhengtoon.framework.utils.HttpUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhengtoon.common.sys.plugin.service.CommonsyspluginService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


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
public class CommonsyspluginServiceImpl implements CommonsyspluginService{

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private SystemConfig systemConfig;

    public AppProjectInitVO appProjectInit(AppProjectInitDTO appProjectInitDTO) {
		AppProjectInitVO appProjectInitVO = new AppProjectInitVO();
		//todo write your code
        appProjectInitVO.setDomainUrl(systemConfig.getDomainUrl());
        appProjectInitVO.setUploadMuiltipart(systemConfig.getUploadMuiltipart());
		appProjectInitVO.setThirdPartyCompanyJson(systemConfig.getThirdPartyCompanyJson());
		appProjectInitVO.setPostalCodeUrl(systemConfig.getPostalCodeUrl());
		appProjectInitVO.setPostageUrl(systemConfig.getPostageUrl());
		return appProjectInitVO;
	}


    public PcProjectInitVO pcProjectInit(PcProjectInitDTO pcProjectInitDTO) {
		PcProjectInitVO pcProjectInitVO = new PcProjectInitVO();
		//todo write your code
        pcProjectInitVO.setDomainUrl(systemConfig.getDomainUrl());
        pcProjectInitVO.setUploadMuiltipart(systemConfig.getUploadMuiltipart());
		return pcProjectInitVO;
	}


    public PcUploadFileVO pcUploadFile(MultipartFile file) {
		PcUploadFileVO pcUploadFileVO = new PcUploadFileVO();
		try {
			String fileName = file.getOriginalFilename();
			int split = fileName.lastIndexOf(".");
			String fileType = fileName.substring(split + 1, fileName.length());
			fileType = "."+fileType;
			String mimeType = "";
			if(fileType.endsWith("doc")||fileType.endsWith("xls")){
				mimeType = "application/vnd";
				fileType = fileType+"x";
			}else {
				mimeType = file.getContentType();
			}

			okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(MediaType.parse(mimeType) , file.getBytes());
			okhttp3.RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM)
					//可以根据自己的接口需求在这里添加上传的参数
					.addFormDataPart("multipartFile", URLEncoder.encode(file.getName()+fileType,"utf-8"), fileBody)
					.build();

			Request request = new Request.Builder()
					.url(systemConfig.getUploadMuiltipart())
					.post(requestBody)
					.build();

			Response response = HttpUtils.execute(request);
			if (response.isSuccessful()){
				String json = response.body().string();
				JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("data");
				pcUploadFileVO.setFileUrl(jsonObject.getString("fileUrl"));
			}

		} catch (Exception e) {
			throw new BusinessException(CoreExceptionCodes.UNKNOWN_ERROR.getCode(), "上传图片失败!");
		}
		return pcUploadFileVO;
	}


    public AppUploadFileVO appUploadFile(MultipartFile file) {
		AppUploadFileVO appUploadFileVO = new AppUploadFileVO();
		try {
			String fileName = file.getOriginalFilename();
			int split = fileName.lastIndexOf(".");
			String fileType = fileName.substring(split + 1, fileName.length());
			fileType = "."+fileType;
			String mimeType = "";
			if(fileType.endsWith("doc")||fileType.endsWith("xls")){
				mimeType = "application/vnd";
				fileType = fileType+"x";
			}else {
				mimeType = file.getContentType();
			}

			okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(MediaType.parse(mimeType) , file.getBytes());
			okhttp3.RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM)
					//可以根据自己的接口需求在这里添加上传的参数
					.addFormDataPart("multipartFile", URLEncoder.encode(file.getName()+fileType,"utf-8"), fileBody)
					.build();

			Request request = new Request.Builder()
					.url(systemConfig.getUploadMuiltipart())
					.post(requestBody)
					.build();

			Response response = HttpUtils.execute(request);
			if (response.isSuccessful()){
				String json = response.body().string();
				JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("data");
				appUploadFileVO.setFileUrl(jsonObject.getString("fileUrl"));
			}

		} catch (Exception e) {
			throw new BusinessException(CoreExceptionCodes.UNKNOWN_ERROR.getCode(), "上传图片失败!");
		}
		return appUploadFileVO;
	}

	@Override
	public Map<String, String> pcValidateAuthCode(String code) {
		Map<String, String> mapResult = new HashMap<>();

		try {
			//请求accessToken
			String response = HttpUtils.syncGetString(systemConfig.getUumGetTokenUrl() + code);
			JSONObject accessTokenVOResponseResult = JSONObject.parseObject(response);
			if (CommonConstants.INTEGER_ZERO.equals(accessTokenVOResponseResult.getInteger("code"))) {
				String accessToken = accessTokenVOResponseResult.getJSONObject("data").getString("accessToken");
				//accessToken换取用户信息
				Map<String,String> map = new HashMap<>();
				map.put("X-Access-Token", accessToken);
				String responseUserInfo = HttpUtils.syncGetString(systemConfig.getGetUserInfo(), Headers.of(map));
				JSONObject oauthUserInfoResponseResult = JSONObject.parseObject(responseUserInfo);
				if (CommonConstants.INTEGER_ZERO.equals(oauthUserInfoResponseResult.getInteger("code"))) {
					String sessionId = UUID.randomUUID().toString();
					mapResult.put("sessionId", sessionId);
					stringRedisTemplate.opsForValue().set(systemConfig.getSessionKey() + sessionId, JSONObject.toJSONString(oauthUserInfoResponseResult.getJSONObject("data")), 1800L, TimeUnit.SECONDS);
					return mapResult;
				} else {
					throw new BusinessException(CoreExceptionCodes.UNKNOWN_ERROR.getCode(), "解析code失败");
				}
			} else {
				throw new BusinessException(CoreExceptionCodes.UNKNOWN_ERROR.getCode(), "获取code失败");
			}
		} catch (Exception e) {
			throw new BusinessException(CoreExceptionCodes.UNKNOWN_ERROR.getCode(), "code换取用户信息失败");
		}
	}
}