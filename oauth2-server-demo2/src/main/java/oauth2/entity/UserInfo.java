package oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 25/1/2022 下午10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userName;
    private String password;
}
