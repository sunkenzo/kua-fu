package com.mini.kuafu.service.auth.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Splitter;
import com.mini.kuafu.common.session.Session;
import com.mini.kuafu.common.session.SessionConfig;
import com.mini.kuafu.common.session.SessionManager;
import com.mini.kuafu.service.user.domain.User;
import com.mini.kuafu.service_client.AuthService;
import com.mini.kuafu.service_client.UserService;
import com.mini.kuafu.util.TimeHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kenzo
 * @create 2021-02-06 10:31
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final SessionManager sessionManager;

    public AuthServiceImpl(UserService userService,
                           SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @Override
    public String login(String username, String password) {
        User user = userService.getOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getAccount, username.trim()));
        if (user == null) {
            return "账号不存在";
        }
        if (!password.equals(user.getPassword())) {
            return "账号或密码错误";
        }

        Session session = sessionManager.creat();
        session.push("userId", String.valueOf(user.getId()));
        session.push("account", user.getAccount());
        session.push("birthday", TimeHelper.formatDate(user.getBirthday()));

        SessionConfig webSessionConfig = SessionManager.WEB_SESSION_CONFIG;

        sessionManager.manage(session, webSessionConfig);

        return session.getSessionId() + "#" + user.getId();
    }

    @Override
    public void logout(String token) {
        List<String> list = Splitter.on("#").trimResults().splitToList(token);
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("token error");
        }

        sessionManager.delete(list.get(0), SessionManager.WEB_SESSION_CONFIG);
    }
}
