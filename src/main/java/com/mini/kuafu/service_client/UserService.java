package com.mini.kuafu.service_client;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.kuafu.api.auth.Login;
import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service.user.domain.User;

import javax.servlet.http.HttpSession;

/**
 * @author kenzo
 * @create 2021-02-05 13:39
 */
public interface UserService extends IService<User> {
    ApiResult login(Login.Payload payload, HttpSession session);
}
