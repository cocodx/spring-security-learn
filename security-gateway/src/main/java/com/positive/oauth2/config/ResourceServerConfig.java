package com.positive.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 网关加一系列的资源的类
 * uaa order
 * @date 16/2/2022 下午9:21
 */
@Configuration
//@EnableResourceServer//标记是一个资源服务
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //资源服务id要和授权服务id是一致的
    public static final String RESOURCE_ID = "res1";

    @Configuration
    @EnableResourceServer
    public class UAAServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID)
//                .tokenServices(tokenService())//验证令牌的服务
                    .stateless(true)
            ;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/uaa/**")
                    .permitAll()
            ;
        }
    }

    @Configuration
    @EnableResourceServer
    public class OrderServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID)
//                .tokenServices(tokenService())//验证令牌的服务
                    .stateless(true)
            ;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/order/**")
                    .access("#oauth2.hasScope('ROLE_API')")
            ;
        }
    }













}
