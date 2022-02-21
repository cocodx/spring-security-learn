package com.positive.oauth2;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午12:10
 */
@SpringBootApplication
@EnableApolloConfig
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class,args);
    }
}
