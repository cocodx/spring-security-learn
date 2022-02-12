package com.positive.oauth2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 12/2/2022 下午6:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    public static final String SESSION_USER_KEY="_user";
    //用户身份信息
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String mobile;

    //用户权限
    private Set<String> authorities;
}
