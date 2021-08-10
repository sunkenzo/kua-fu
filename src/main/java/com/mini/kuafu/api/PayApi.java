package com.mini.kuafu.api;

import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service.pay.listener.PayEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-06-29 16:28
 */
@RestController
public class PayApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    static final String PATH = "/pay/test";

    private ApplicationEventPublisher applicationEventPublisher;

    public PayApi(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping(PATH)
    public ApiResult process(long userId) {
        logger.info("{}", "step 1. ......");
        applicationEventPublisher.publishEvent(new PayEvent("", userId));
        logger.info("{}", "step 3. ......");
        return ApiResult.ok();
    }
}
