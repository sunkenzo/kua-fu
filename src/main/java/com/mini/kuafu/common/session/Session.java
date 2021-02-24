package com.mini.kuafu.common.session;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * session 会话
 *
 * @author kenzo
 * @create 2021-02-24 14:40
 */
@Setter
@Getter
public class Session {
    private String sessionId;
    private Map<String, String> attr = Maps.newHashMap();

    public void push(String key, String value) {
        attr.put(key, value);
    }
}
