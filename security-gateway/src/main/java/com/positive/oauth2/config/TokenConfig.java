package com.positive.oauth2.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author 正能量导师
 * @version 1.0
 * @description tokenStore
 * @date 15/2/2022 上午6:25
 */
@Data
@Configuration
public class TokenConfig {

    private String SIGN_KEY="uaa123";

    //令牌存储策略
//    @Bean
//    public TokenStore tokenStore(){
//        //内存方式，生成普通令牌
//        return new InMemoryTokenStore();
//    }

    @Bean
    public TokenStore tokenStore(){
        //jwt令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGN_KEY);//对称密钥，资源服务器使用该密钥来验证
        return converter;
    }
}
