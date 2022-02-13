package com.positive.oauth2.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author 正能量导师
 * @version 1.0
 * @description spring security 初始化，在有spring和springMvc的情况下，如下图所示
 * 在没有的情况下，就是调用super的方法
 * @date 13/2/2022 下午8:52
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityInitializer() {
    }
}
