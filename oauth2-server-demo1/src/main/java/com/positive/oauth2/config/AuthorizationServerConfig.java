package com.positive.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 25/1/2022 下午9:32
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置客户端详情服务，给谁发令牌
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("clientApp").secret("{noop}112233")
                .redirectUris("http://localhost:8001/payment/get/1")
                .authorizedGrantTypes("authorization_code")
                .scopes("read_userInfo","read_contacts");
    }
}
