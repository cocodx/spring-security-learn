package com.positive.oauth2.interceptor;

import com.positive.oauth2.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 正能量导师
 * @version 1.0
 * @description filter interceptor
 * @date 12/2/2022 下午8:25
 */
@Component
public class SimpleAuthenticationHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (object==null){
            writeContent(response,"请登录");
        }
        UserDto userDto = (UserDto) object;
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("r1") && requestURI.contains("/r1")){
            return true;
        }
        if (userDto.getAuthorities().contains("r2") && requestURI.contains("/r2")){
            return true;
        }
        writeContent(response,"没有权限，拒绝访问");
        return false;
    }

    /**
     * 响应消息给客户端
     * @param response
     * @param msg
     * @throws IOException
     */
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(msg);
        printWriter.close();
    }
}
