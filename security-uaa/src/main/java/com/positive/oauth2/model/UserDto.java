package com.positive.oauth2.model;

import lombok.Data;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午6:06
 */
@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
