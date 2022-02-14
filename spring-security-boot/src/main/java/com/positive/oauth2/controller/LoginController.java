package com.positive.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午6:27
 */
@RestController
public class LoginController {

    @PreAuthorize("hasAuthority('p1')")
    @RequestMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    public String r1(){
        return getUsername()+"r1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @RequestMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    public String r2(){
        return getUsername()+"r2";
    }

    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if (principal==null){
            username="匿名";
        }
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else {
            username = principal.toString();
        }
        return username;
    }
}
