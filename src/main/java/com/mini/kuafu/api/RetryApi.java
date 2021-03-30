package com.mini.kuafu.api;

import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.service.pay.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-03-24 16:07
 */
@RestController
public class RetryApi {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayService payService;

    @GetMapping("/createOrder")
    public ApiResult createOrder(@RequestParam int num) {
        try {
            int remainingnum = payService.minGoodsnum(num == 0 ? 1 : num);
            logger.info("剩余的数量===" + remainingnum);

            return ApiResult.ok();
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            return ApiResult.error(e.getMessage());
        }
    }

}
