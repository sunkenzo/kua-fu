package com.mini.kuafu.service.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kenzo
 * @create 2021-02-05 15:08
 */
@Setter
@Getter
@Builder
public class BaiDuTranslationConfig {

    private String baseUrl;
    private String appId;
    private String appSecret;
}
