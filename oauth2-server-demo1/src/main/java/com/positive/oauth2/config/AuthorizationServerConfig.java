package com.positive.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 授权服务
 * @date 25/1/2022 下午9:32
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;
    @Autowired
    ClientDetailsService clientDetailsService;
//    @Autowired
//    AuthorizationCodeServices authorizationCodeServices;
//    @Autowired
//    AuthenticationManager authenticationManager;

    /**
     * 配置客户端详情服务，给谁发令牌
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("clientApp").secret("{noop}112233")
                .redirectUris("http://localhost:8001/payment/get/1")//验证回调地址
                .authorizedGrantTypes("authorization_code")//支持的授权类型
                .scopes("read_userInfo","read_contacts")//允许访问的范围
                .autoApprove(false);//false 跳转到授权界面
    }

    //配置令牌访问服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);//客户端信息服务
        services.setSupportRefreshToken(true);//是否产生刷新令牌
        services.setTokenStore(tokenStore);//令牌存储策略
        services.setAccessTokenValiditySeconds(7200);//令牌默认有效期2小时
        services.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        return services;
    }

    //令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .authenticationManager(authenticationManager)//密码模式需要
//                .authorizationCodeServices(authorizationCodeServices)//授权码模式需要
                .tokenServices(tokenServices())//令牌管理的服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许post提交
    }

    //令牌访问端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")//oauth/token_key 公开的
                .checkTokenAccess("permitAll()")//oauth/check_token 公开的
                .allowFormAuthenticationForClients();//允许表单来申请令牌
    }
}
