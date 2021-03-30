package com.mini.kuafu.api.auth;

import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service_client.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kenzo
 * @create 2021-03-30 15:42
 */
@RestController
public class Login extends AuthCommon {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    public Login(HttpServletRequest request,
                 HttpServletResponse response,
                 UserService userService) {
        super(request, response);
        this.userService = userService;
    }

    @PostMapping("/login")
    private ApiResult process(@RequestBody @Validated Payload payload) {
        return userService.login(payload, this.getSession());
    }

    @Setter
    @Getter
    @ToString
    public static class Payload {
        private String account;
        private String password;
        private String validCode;
    }
}
