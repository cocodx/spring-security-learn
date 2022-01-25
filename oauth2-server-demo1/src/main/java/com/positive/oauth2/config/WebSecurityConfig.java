package com.positive.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 正能量导师
 * @version 1.0
 *
 * User must be authenticated with Spring Security before authorization can be completed.
 *
 * @description spring security高版本 开启安全认证
 *
 * 这里配置了用户，在yml配置的就会失效
 *
 * @date 25/1/2022 下午9:47
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password(new CustomPasswordEncoder().encode("123456")).roles("SuperAdmin")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
    }
}
