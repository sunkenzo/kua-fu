package com.mini.kuafu.api;

import com.mini.kuafu.service.baidu.impl.BaiDuApi;
import com.mini.kuafu.service.user.domain.User;
import com.mini.kuafu.service_client.BaiduServer;
import com.mini.kuafu.service_client.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-02-04 17:48
 */
@RestController
@RequestMapping("auth")
public class api {

    private final UserService userService;
    private final BaiduServer baiduServer;

    public api(UserService userService,
               BaiduServer baiduServer) {
        this.userService = userService;
        this.baiduServer = baiduServer;
    }

    @PostMapping("/login")
    public String process() {
        User user = new User();
        user.setUserName("蔡元培");
        userService.save(user);
        return "sucess";
    }

    @PostMapping("/translation")
    public Object translation() {

        return baiduServer.translation("苹果");
    }


}
