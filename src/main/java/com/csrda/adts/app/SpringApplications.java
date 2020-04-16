package com.csrda.adts.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/** 
 * @ClassName: SpringApplications 
 * @Description: 启动类
 * @author: wangwei
 * @date: 2020年3月14日 下午2:31:28 
 */
                 

@SpringBootApplication(scanBasePackages="com.csrda.adts.*")
@MapperScan("com.csrda.adts.dao")
public class SpringApplications {
	public static void main(String[] args) {
		SpringApplication.run(SpringApplications.class, args);
	}
}
