package com.positive.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 自动装配
 * @date 13/2/2022 下午8:39
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //定义用户服务信息，查询用户信息
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("1q2w3e").authorities("p1").build());
//        inMemoryUserDetailsManager.createUser(User.withUsername("lisi").password("1q2w3e").authorities("p2").build());
//        return inMemoryUserDetailsManager;
//    }

    //密码解码器
    @Bean
    public static PasswordEncoder passwordEncoder(){
        //明文比对
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**必须认证通过
                .anyRequest().permitAll()//除了/r/**其他请求可以访问
                .and()
                .formLogin()//允许表单登录
                .successForwardUrl("/login-success")//自定义登录成功的界面地址
        ;
    }
}
