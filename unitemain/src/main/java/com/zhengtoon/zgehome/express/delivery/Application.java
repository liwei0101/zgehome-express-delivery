/**
 * @author 
 */
package com.zhengtoon.zgehome.express.delivery;

import lombok.extern.slf4j.Slf4j;
import com.zhengtoon.framework.disconf.EnableDisconfProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用服务启动类
 *
 * @author Administrator
 * @create 2018-01-02 20:39
 * Copyright: Copyright (c) 2018
 * Company:github
 **/

@Slf4j
@SpringBootApplication(
scanBasePackages = {
"com.zhengtoon.zgehome.express.delivery*"
	,"com.zhengtoon.config.loaded.plugin*"
	,"com.zhengtoon.common.sys.plugin*"
	,"com.zhengtoon.pc.express.company*"
	,"com.zhengtoon.app.express.company*"
})
@EnableDisconfProperties
@MapperScan({"com.zhengtoon.zgehome.express.delivery.mapper","0"})
@EnableSwagger2
public class Application {
     public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(Application.class)
                .main(Application.class)
                .run(args);
        log.info("----Application Start PID={}----", new ApplicationPid().toString());
        context.registerShutdownHook();
    }
}