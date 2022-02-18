package com.positive.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 19/2/2022 上午1:37
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class,args);
    }
}
