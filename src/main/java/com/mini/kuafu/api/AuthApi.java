package com.mini.kuafu.api;

import com.mini.kuafu.service_client.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kenzo
 * @create 2021-02-06 10:54
 */
@RestController
public class AuthApi {
    static final String PATH = "/auth/login";

    @Resource
    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(PATH)
    public String process() {
        return authService.login("", "");
    }
}
