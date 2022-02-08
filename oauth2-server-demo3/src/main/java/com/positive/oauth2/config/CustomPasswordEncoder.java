package com.positive.oauth2.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 正能量导师
 * @version 1.0
 * @description 自定义密码加密类，实现PasswordEncoder接口，不加密
 * @date 25/1/2022 下午9:50
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
