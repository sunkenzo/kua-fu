package com.mini.kuafu.service.baidu.impl;

import com.mini.kuafu.service.baidu.dto.BaiDuTranslationResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * 百度开放平台
 *
 * @author kenzo
 * @create 2021-02-05 15:43
 */
public interface BaiDuApi {

    /**
     *  翻译
     *
     * @param requestParam
     * @return
     */
    @POST("/api/trans/vip/translate")
    Call<BaiDuTranslationResponse> translation(@QueryMap Map<String, Object> requestParam);
}
