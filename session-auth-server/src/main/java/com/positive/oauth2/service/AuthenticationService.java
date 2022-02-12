package com.positive.oauth2.service;

import com.positive.oauth2.model.AuthenticationRequest;
import com.positive.oauth2.model.UserDto;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 认证接口
 * @date 12/2/2022 下午6:17
 */
public interface AuthenticationService {

    /**
     * user auth
     * @param request userName password
     * @return userInfo
     */
    UserDto authentication(AuthenticationRequest request);
}
