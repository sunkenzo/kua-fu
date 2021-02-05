package com.mini.kuafu.service.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author kenzo
 * @create 2021-02-05 14:37
 */
@Component
@Profile({"default", "test", "dev"})
public class TestProfileConfig implements ProfileConfig {

    @Override
    public String getOssEndPoint() {
        return "kuafu-test.mini.com";
    }

    @Override
    public BaiDuTranslationConfig getBaiDuTranslationConfig() {
        return BaiDuTranslationConfig.builder()
                .baseUrl("http://api.fanyi.baidu.com")
                .appId("20210205000691383")
                .appSecret("Q5FixcBQ7wNZpJzrQQrp")
                .build();
    }
}
