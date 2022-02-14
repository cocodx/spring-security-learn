package com.positive.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 启动类
 * @date 15/2/2022 上午6:02
 */
@EnableHystrix//熔断
@EnableFeignClients(basePackages = {"com.positive.oauth2"})//远程调用
@SpringBootApplication
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class,args);
    }
}
