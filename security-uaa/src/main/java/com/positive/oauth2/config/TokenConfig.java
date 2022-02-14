package com.positive.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author 正能量导师
 * @version 1.0
 * @description tokenStore
 * @date 15/2/2022 上午6:25
 */
@Configuration
public class TokenConfig {

    //令牌存储策略
    @Bean
    public TokenStore tokenStore(){
        //内存方式，生成普通令牌
        return new InMemoryTokenStore();
    }
}
