package com.mini.kuafu.api.auth;

import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kenzo
 * @create 2021-03-30 17:22
 */
@RestController
public class GetUserInfo extends AuthCommon {

    public GetUserInfo(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @PostMapping("/getUserInfo")
    private ApiResult process() {
        User curUser = this.getCurUser();
        if (curUser == null) {
            return ApiResult.error("用户已过期，请重新登陆");
        } else {
            return ApiResult.ok(curUser);
        }
    }
}
