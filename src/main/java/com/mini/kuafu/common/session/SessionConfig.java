package com.mini.kuafu.common.session;

import lombok.Getter;
import lombok.Setter;

/**
 * session 配置类
 *
 * @author kenzo
 * @create 2021-02-24 14:50
 */
@Setter
@Getter
public class SessionConfig {
    private String accessToken;
    private Integer expire;

    public SessionConfig(String accessToken, Integer expire) {
        this.accessToken = accessToken;
        this.expire = expire;
    }
}
