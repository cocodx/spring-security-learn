package com.positive.oauth2.service;

import com.positive.oauth2.model.AuthenticationRequest;
import com.positive.oauth2.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 12/2/2022 下午6:20
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public UserDto authentication(AuthenticationRequest request) {
        if (request==null
        || StringUtils.isEmpty(request.getUserName())
        || StringUtils.isEmpty(request.getPassword())){
            throw new RuntimeException("账号或者密码为空");
        }
        UserDto userDto = getUserDto(request.getUserName());
        if (userDto == null){
            throw new RuntimeException("查询不到该用户");
        }
        if (!request.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或者密码错误");
        }
        return userDto;
    }

    //simulated database search
    private UserDto getUserDto(String userName) {
        return initUserInfo.get(userName);
    }

    //users info Map Collection
    private static Map<String,UserDto> initUserInfo = new HashMap<>();

    //init users info
    static {
        Set<String> auth1 = new HashSet<>();
        auth1.add("r1");
        Set<String> auth2 = new HashSet<>();
        auth2.add("r2");
        initUserInfo.put("张三",new UserDto("1011","张三","1q2w3e","张三","18162775609",auth1));
        initUserInfo.put("李四",new UserDto("1011","张三","1q2w3e","李四","19522978908",auth2));
    }


}
