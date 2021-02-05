package com.mini.kuafu.service.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author kenzo
 * @create 2021-02-05 14:35
 */
@Component
@Profile("prod")
public class ProdProfileConfig implements ProfileConfig {

    @Override
    public String getOssEndPoint() {
        return "kuafu-prod.mini.com";
    }

    @Override
    public BaiDuTranslationConfig getBaiDuTranslationConfig() {
        return BaiDuTranslationConfig.builder()
                .baseUrl("https://fanyi-api.baidu.com")
                .appId("20210205000691383")
                .appSecret("Q5FixcBQ7wNZpJzrQQrp")
                .build();
    }
}
