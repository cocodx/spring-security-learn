package com.positive.oauth2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 3.0之后，就不用web.xml了
 * Spring容器的配置，排除掉controller注解的扫描，就跟以前的web项目，有一个专门的xml配置springmvc
 *
 *
 * @date 12/2/2022 下午5:44
 */
@Configuration
@ComponentScan(basePackages = "com.positive.oauth2"
,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {


}
