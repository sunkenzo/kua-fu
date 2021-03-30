package com.mini.kuafu.api.auth;

import com.mini.kuafu.common.response.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kenzo
 * @create 2021-03-30 16:56
 */
@RestController
public class Logout extends AuthCommon {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Logout(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @PostMapping("/logout")
    private ApiResult process() {
        this.getSession().removeAttribute(AuthCommon.USER_INFO_KEY);

        return ApiResult.ok();
    }
}
