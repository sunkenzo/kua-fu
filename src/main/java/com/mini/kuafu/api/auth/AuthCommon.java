package com.mini.kuafu.api.auth;

import com.mini.kuafu.service.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kenzo
 * @create 2021-03-30 17:00
 */
@Setter
@Getter
@NoArgsConstructor
public class AuthCommon {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public static final String VALID_CODE_KEY = "validCode";
    public static final String USER_INFO_KEY = "userInfo";

    public AuthCommon(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public User getCurUser() {
        HttpSession session = this.getSession();
        Object attribute = session.getAttribute(AuthCommon.USER_INFO_KEY);
        if (attribute instanceof User) {
            return (User) attribute;
        }

        return null;
    }

    public HttpSession getSession() {
        return this.request.getSession();
    }

}
