package com.positive.oauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 13/2/2022 下午9:02
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = "text/plain;charset=utf-8")
    public String logout(){
        return "登录成功";
    }

    @RequestMapping("/r/r1")
    public String r1(){
        return "r/r1";
    }

    @RequestMapping("/r/r2")
    public String r2(){
        return "r/r2";
    }
}
