package com.positive.oauth2.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 返回apollo配置的值
 * @date 22/2/2022 上午6:53
 */
@Slf4j
@RestController
public class TestApolloValueController {

    //Apollo服务端的中的配置注入这个类中
    @ApolloConfig
    Config config;

    //监听配置中心配置的更新事件，
    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent configChangeEvent){
        refreshLoggingLevels();
    }

    @PostConstruct
    private void refreshLoggingLevels(){
        Set<String> keyNames = config.getPropertyNames();
        for (String key:keyNames) {
            String values = config.getProperty(key,"info");
            log.info("{}:{}",key,values);
        }
    }

    @RequestMapping("/getValues/{key}")
    public String getValues(@PathVariable("key")String key){
        log.info("前端访问key：{}",key);
        return config.getProperty(key,"info");
    }
}
