package com.positive.oauth2.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.positive.oauth2.common.EncryptUtil;
import com.positive.oauth2.model.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 19/2/2022 上午1:16
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("json-token");
        if (token!=null){
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            //将token转成json对象
            JSONObject jsonObject = JSON.parseObject(json);
            String principal = jsonObject.getString("principal");

            UserDto userDto = new UserDto();
            userDto.setUsername(principal);

            JSONArray jsonArray = jsonObject.getJSONArray("authorities");
            String[] strings = jsonArray.toArray(new String[jsonArray.size()]);

            //填充Authentication，用户身份的token中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto,null, AuthorityUtils.createAuthorityList(strings));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //将对象填充到 springsecurity的上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
