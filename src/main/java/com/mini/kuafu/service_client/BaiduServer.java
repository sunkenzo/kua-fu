package com.mini.kuafu.service_client;

import com.mini.kuafu.service.baidu.dto.BaiDuTranslationResponse;

/**
 * @author kenzo
 * @create 2021-02-05 15:16
 */
public interface BaiduServer {

   BaiDuTranslationResponse translation(String cnChar);
}
