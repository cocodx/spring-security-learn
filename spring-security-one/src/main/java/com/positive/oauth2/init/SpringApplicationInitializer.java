package com.positive.oauth2.init;

import com.positive.oauth2.config.ApplicationConfig;
import com.positive.oauth2.config.WebConfig;
import com.positive.oauth2.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 这个类实现WebApplicationInitializer接口
 * @date 12/2/2022 下午5:58
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //spring容器，相当于加载applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    //刚才配置的servletContext webConfig
    //servletContext,相当于加载springMvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
