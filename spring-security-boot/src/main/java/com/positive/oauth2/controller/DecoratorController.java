package com.positive.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午5:37
 */
@Controller
public class DecoratorController {

    @RequestMapping("/login-success")
    public ModelAndView success(){
        return new ModelAndView("/login-success");
    }
}
