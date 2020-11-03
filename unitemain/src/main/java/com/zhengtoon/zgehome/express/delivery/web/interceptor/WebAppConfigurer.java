/**
 * 
 */
package com.zhengtoon.zgehome.express.delivery.web.interceptor;

import com.zhengtoon.config.loaded.plugin.interceptor.PcSessionInterceptor;
import com.zhengtoon.config.loaded.plugin.service.PcSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhengtoon.framework.uias.interceptor.SessionInterceptor;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public PcSessionInterceptor pcSessionInterceptor(PcSessionService pcSessionService) {
        return new PcSessionInterceptor(pcSessionService);
    }

    @Autowired
    private PcSessionInterceptor pcSessionInterceptor;

    @Autowired
    private SessionInterceptor sessionInterceptor;

    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    private static final String[] APP_PATH_PATTERNS = {"/app/**"};

    private static final String[] APP_EXCLUDE_PATH_PATTERNS = {"/v1/open/**", "/app/appProjectInit/**", "/open/**", "/v2/open/**"};

    private static final String[] PC_PATH_PATTERNS = {"/pc/**"};

    private static final String[] PC_EXCLUDE_PATH_PATTERNS = {"/pc/open/**", "/pc/pcValidateAuthCode/**", "/pc/pcProjectInit/**", "/open/**", "/pc/index.html"};

    /**
     * 获取用户session拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns(APP_PATH_PATTERNS)
                .excludePathPatterns(APP_EXCLUDE_PATH_PATTERNS);

        registry.addInterceptor(pcSessionInterceptor)
                .addPathPatterns(PC_PATH_PATTERNS)
                .excludePathPatterns(PC_EXCLUDE_PATH_PATTERNS);

     }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
}