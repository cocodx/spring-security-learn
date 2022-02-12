package com.positive.oauth2.model;

import lombok.Data;

/**
 * @author 正能量导师
 * @version 1.0
 * @description user info
 * @date 12/2/2022 下午6:18
 */
@Data
public class AuthenticationRequest {

    private String userName;

    private String password;
}
