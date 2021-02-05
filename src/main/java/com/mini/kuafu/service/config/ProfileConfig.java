package com.mini.kuafu.service.config;

/**
 * @author kenzo
 * @create 2021-02-05 14:32
 */
public interface ProfileConfig {

    String getOssEndPoint();

    /** 百度翻译 */
    BaiDuTranslationConfig getBaiDuTranslationConfig();
}
