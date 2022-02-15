package com.positive.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 自动装配
 * @date 13/2/2022 下午8:39
 */
@Configuration
//激活权限校验的注解
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //定义用户服务信息，查询用户信息
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("1q2w3e").authorities("p1").build());
//        inMemoryUserDetailsManager.createUser(User.withUsername("lisi").password("1q2w3e").authorities("p2").build());
//        return inMemoryUserDetailsManager;
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }


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
        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1")
//                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**必须认证通过
                .anyRequest().permitAll()//除了/r/**其他请求可以访问
//                .and()
//                .formLogin()//允许表单登录
//                .loginPage("/login-view")//登录界面
//                .loginProcessingUrl("/login")//指定登录url
//                .successForwardUrl("/login-success")//自定义登录成功的界面地址
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//        .and()
//        .logout()
//        .logoutUrl("/logout")
//        .logoutSuccessUrl("/login-view?logout")
            .and()
            .httpBasic()
        ;
    }
}
