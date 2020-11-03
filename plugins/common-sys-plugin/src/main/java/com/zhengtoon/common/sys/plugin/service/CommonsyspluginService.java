/**
 *
 */
package com.zhengtoon.common.sys.plugin.service;

import com.zhengtoon.common.sys.plugin.bean.vo.AppProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcProjectInitVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcProjectInitDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.PcUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.PcUploadFileDTO;
import com.zhengtoon.common.sys.plugin.bean.vo.AppUploadFileVO;
import com.zhengtoon.common.sys.plugin.bean.dto.AppUploadFileDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 插件Service接口
 *
 * @author admin
 * @version 1.0
 * Copyright: Copyright (c) 2018
 * Company:北京政通科技有限公司
 */
public interface CommonsyspluginService {

	/**
	 * app项目初始化信息
	 *
	 *
	 * @return  boolean
	 */
	AppProjectInitVO appProjectInit(AppProjectInitDTO appProjectInitDTO);
	/**
	 * pc项目初始化信息
	 *
	 *
	 * @return  boolean
	 */
	PcProjectInitVO pcProjectInit(PcProjectInitDTO pcProjectInitDTO);
	/**
	 * pc上传附件
	 *
	 *
	 * @return  boolean
	 */
	PcUploadFileVO pcUploadFile(MultipartFile file);
	/**
	 * app上传附件
	 *
	 *
	 * @return  boolean
	 */
	AppUploadFileVO appUploadFile(MultipartFile file);

	/**
	 * 验证AuthCode-pc工作台跳转
	 * @param code
	 * @return
	 */
	Map<String, String> pcValidateAuthCode(String code);
}