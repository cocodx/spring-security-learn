package oauth2.controller;

import com.positive.oauth2.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 正能量导师
 * @version 1.0
 * @description
 * @date 25/1/2022 下午10:14
 */
@Controller
public class ApiController {

    /**
     * 资源api
     * @return
     */
    @RequestMapping("/api/userInfo")
    public Object getUserInfo(){
        return ResponseEntity.ok(new UserInfo("11","22"));
    }
}
