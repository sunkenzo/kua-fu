package com.mini.kuafu.common.session;

import com.mini.kuafu.util.UuidHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * session容器
 *
 * @author kenzo
 * @create 2021-02-06 10:47
 */
@Component
public class SessionManager {

    public static final SessionConfig WEB_SESSION_CONFIG = new SessionConfig("session:%s", 1);

    private final StringRedisTemplate redis;

    public SessionManager(StringRedisTemplate redisTemplate) {
        this.redis = redisTemplate;
    }

    public Session creat() {
        Session session = new Session();
        session.setSessionId(RandomStringUtils.randomAlphanumeric(8) + UuidHelper.UUID());

        return session;
    }

    public void manage(Session session, SessionConfig config) {
        String key = this.key(config.getAccessToken(), session.getSessionId());

        Boolean exist = redis.hasKey(key);
        redis.opsForHash().putAll(key, session.getAttr());

        if (exist != null && !exist) {
            redis.expire(key, config.getExpire(), TimeUnit.DAYS);
        }
    }

    public void delete(String sessionId, SessionConfig config) {
        String key = this.key(config.getAccessToken(), sessionId);
        redis.delete(key);
    }

    // private method

    private String key(String accessToken, String sessionId) {
        return String.format(accessToken, sessionId);
    }
}
