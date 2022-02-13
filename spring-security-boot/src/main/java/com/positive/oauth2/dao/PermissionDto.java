package com.positive.oauth2.dao;

import lombok.Data;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午7:43
 */
@Data
public class PermissionDto {

    private Long id;
    private String code;
    private String desc;
    private String url;
}
