package com.mini.kuafu.service.pay.aspect;

import com.mini.kuafu.service.user.dao.UserMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kenzo
 * @create 2021-06-29 17:02
 */
@Order(1)
@Aspect
@Component
public class PayAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;

    public PayAspect(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Pointcut("execution(public * com.mini.kuafu.service..*Listener.onApplicationEvent(..))")
    public void methods() {
    }

}
