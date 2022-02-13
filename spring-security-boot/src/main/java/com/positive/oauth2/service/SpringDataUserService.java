package com.positive.oauth2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午1:13
 */
@Slf4j
@Service
public class SpringDataUserService implements UserDetailsService {

    //根据账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用数据库查询账号信息
        log.info("userName:"+username);
        UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$lqDo6e4B2Ekm9I7L6L7t/ONFQqDPshhnvS0DycPRocgcyrbeRCz2C").authorities("p1").build();
        return userDetails;
    }
}
