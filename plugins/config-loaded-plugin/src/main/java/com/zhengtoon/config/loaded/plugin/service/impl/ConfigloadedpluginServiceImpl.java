/**
 *
 */
package com.zhengtoon.config.loaded.plugin.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import com.zhengtoon.config.loaded.plugin.service.ConfigloadedpluginService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


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
public class ConfigloadedpluginServiceImpl implements ConfigloadedpluginService{

	@Autowired
	StringRedisTemplate stringRedisTemplate;


}