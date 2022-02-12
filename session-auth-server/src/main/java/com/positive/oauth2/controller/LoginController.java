package com.positive.oauth2.controller;

import com.positive.oauth2.model.AuthenticationRequest;
import com.positive.oauth2.model.UserDto;
import com.positive.oauth2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 12/2/2022 下午6:29
 */
@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    //produces 设置返回数据类型，text/plain 文本类型
    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest request, HttpSession session) throws UnsupportedEncodingException {
        //暂未解决，如何实现通过代码的方式设置，springmvc的utf-8过滤器，先这样
        request.setUserName(new String(request.getUserName().getBytes("iso-8859-1"),"utf-8"));
        UserDto userDto = authenticationService.authentication(request);
        //save session,keep comunication
        session.setAttribute(UserDto.SESSION_USER_KEY,userDto);
        return userDto.getUserName()+"登录成功";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "退出登录";
    }

    @RequestMapping("/r/r1")
    public String r1(HttpSession session){
        String fullName = null;
        Object o = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (o==null){
            return "匿名";
        }else {
            UserDto userDto = (UserDto) session.getAttribute(UserDto.SESSION_USER_KEY);
            return userDto.getUserName()+"访问r/r1";
        }
    }

    @RequestMapping("/r/r2")
    public String r2(HttpSession session){
        String fullName = null;
        Object o = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (o==null){
            return "匿名";
        }else {
            UserDto userDto = (UserDto) session.getAttribute(UserDto.SESSION_USER_KEY);
            return userDto.getUserName()+"访问r/r2";
        }
    }
}
