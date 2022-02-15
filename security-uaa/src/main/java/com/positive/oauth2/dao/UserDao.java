package com.positive.oauth2.dao;

import com.positive.oauth2.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    //根据userId查找用户权限
    public List<String> findPermissionByUserId(Long userId){
        String sql = "select * from t_permission where id in\n" +
                "(\n" +
                "\tselect permission_id from t_role_permission where role_id in\n" +
                "\t(\n" +
                "\t\tselect role_id from t_user_role where user_id=?)\n" +
                ")";
        List<PermissionDto> list = jdbcTemplate.query(sql,new Object[]{userId},new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
