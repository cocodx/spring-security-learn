package com.positive.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 正能量导师
 * @version 1.0
 * @description web容器配置
 *
 * spring security 提供了拦截用户请求认证的功能，不需要独立定义拦截器了
 *
 * @date 12/2/2022 下午5:47
 */
@Configuration
//这个注解很关键
@EnableWebMvc
@ComponentScan(basePackages = "com.positive.oauth2"
        ,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    //视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //跳转到Spring security的登录界面
        registry.addViewController("/").setViewName("redirect:/login");
    }

    //尝试解决springmvc接收参数中文乱码-无效
    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return converter;
    }
    //尝试解决springmvc接收参数中文乱码-无效
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

}
