package com.positive.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 16/2/2022 下午9:39
 */
@RestController
public class OrderController {

    @PreAuthorize("hasAuthority('p3')")
    @RequestMapping("/r/r1")
    public String r1(){
        return "r1";
    }
}
