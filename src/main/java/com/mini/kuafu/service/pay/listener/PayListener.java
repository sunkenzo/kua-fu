package com.mini.kuafu.service.pay.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author kenzo
 * @create 2021-06-29 16:21
 */
@Component
public class PayListener implements ApplicationListener<PayEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @Override
    public void onApplicationEvent(PayEvent event) {
        logger.info("{}", "step 2. ......");
    }
}
