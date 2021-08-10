package com.mini.kuafu.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.kuafu.api.auth.AuthCommon;
import com.mini.kuafu.api.auth.Login;
import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service.user.dao.UserMapper;
import com.mini.kuafu.service.user.domain.User;
import com.mini.kuafu.service_client.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author kenzo
 * @create 2021-02-05 13:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ApiResult login(Login.Payload payload, HttpSession session) {
        String validCode = (String) session.getAttribute(AuthCommon.VALID_CODE_KEY);
        if (StringUtils.isBlank(validCode) || !validCode.equalsIgnoreCase(validCode)) {
            return ApiResult.error("验证码错误");
        }

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getAccount, payload.getAccount())
        );
        if (user == null) {
            return ApiResult.error("用户不存在");
        }
        if (!user.getPassword().equals(payload.getPassword())) {
            return ApiResult.error("用户名或密码不正确");
        }

        session.removeAttribute(AuthCommon.VALID_CODE_KEY);
        session.setAttribute(AuthCommon.USER_INFO_KEY, user);

        return ApiResult.ok();
    }
}
