package com.positive.oauth2.dao;

import com.positive.oauth2.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午6:07
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username=?";
        List<UserDto> list = jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
        if (list==null && list.size()<=0){
            return null;
        }
        return list.get(0);

    }
}
