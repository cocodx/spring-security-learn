package com.positive.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 14/2/2022 上午1:24
 */
@RunWith(SpringRunner.class)
public class SpringBootTest {

    @Test
    public void testBCrypt(){
        //对密码进行加密
        String hashpw = BCrypt.hashpw("1q2w3e",BCrypt.gensalt());
        System.out.println(hashpw);

        //校验密码
        boolean checkpw = BCrypt.checkpw("1q2w3e","$2a$10$lqDo6e4B2Ekm9I7L6L7t/ONFQqDPshhnvS0DycPRocgcyrbeRCz2C");
        boolean checkpw2 = BCrypt.checkpw("1q2w3e","$2a$10$2Gxzv1miJu6UA4M201YFb.MCEvpra89VtOg2ei1fWZ84Qd/HFDidm");
        System.out.println(checkpw);
        System.out.println(checkpw2);
    }
}
