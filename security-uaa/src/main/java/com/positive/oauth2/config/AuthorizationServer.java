package com.positive.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 授权服务器配置
 * @date 15/2/2022 上午6:13
 */
@Configuration
@EnableAuthorizationServer//授权服务
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);//客户端信息服务
        services.setSupportRefreshToken(true);//是否产生刷新令牌
        services.setTokenStore(tokenStore);//令牌存储策略
        //设置令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        services.setAccessTokenValiditySeconds(7200);//令牌默认2小时
        services.setRefreshTokenValiditySeconds(259200);//刷新令牌默认3天
        return services;
    }

    //配置令牌端点的安全约束，例如哪些url拦截校验，哪些放行
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")//oauth/token_key是公开 公钥公开
                .checkTokenAccess("permitAll()")//检查令牌 /oauth/check_token
                .allowFormAuthenticationForClients()//表单认证
                ;
    }

    //客户端详情，oauth2协议，客户端鉴权，clientId和clientSecret，配置ClientDetailsService，存在内存还是数据库
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //把客户端的信息存储到数据库
        clients.withClientDetails(clientDetailsService);
//        clients.inMemory().withClient("c1")//客户端id
//                .secret(new BCryptPasswordEncoder().encode("secret"))//客户端密钥
//                .resourceIds("res1")//资源列表
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")//该client允许的五种协议类型
//                .scopes("all")//允许的授权范围
//                .autoApprove(false)//false的时候，在申请授权码的时候，跳转到授权界面让用户授权
//                .redirectUris("http://www.baidu.com")//验证回调地址
//        ;
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService)clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    //配置令牌token的访问端点和令牌服务token services，内存模式，jdbc，jwt 管理令牌的
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)//密码模式，用此类
                .authorizationCodeServices(authorizationCodeServices)//授权码模式，用此类
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                ;
    }
}
