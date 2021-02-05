package com.mini.kuafu.service.baidu.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Maps;
import com.mini.kuafu.service.baidu.dto.BaiDuTranslationResponse;
import com.mini.kuafu.service.config.BaiDuTranslationConfig;
import com.mini.kuafu.service.config.ProfileConfig;
import com.mini.kuafu.service_client.BaiduServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Map;

/**
 * @author kenzo
 * @create 2021-02-05 15:17
 */
@Slf4j
@Service
public class BaiduServerImpl implements BaiduServer {

    private BaiDuTranslationConfig baiDuTranslationConfig;
    private final ObjectMapper objectMapper;
    private final BaiDuApi baiDuApi;

    public BaiduServerImpl(ProfileConfig profileConfig) {

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        this.baiDuTranslationConfig = profileConfig.getBaiDuTranslationConfig();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baiDuTranslationConfig.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        this.baiDuApi = retrofit.create(BaiDuApi.class);
    }

    @SneakyThrows
    @Override
    public BaiDuTranslationResponse translation(String cnChar) {
        String q = cnChar;
        String from = "zh";
        String to = "en";
        String appId = baiDuTranslationConfig.getAppId();
        String salt = String.valueOf(System.currentTimeMillis());
        String appSecret = baiDuTranslationConfig.getAppSecret();

        String sign = appId + q + salt + appSecret;
        sign = DigestUtils.md5DigestAsHex(sign.getBytes());

        // requestPayload
        Map<String, Object> requestPayload = Maps.newHashMap();
        requestPayload.put("q", q);
        requestPayload.put("from", from);
        requestPayload.put("to", to);
        requestPayload.put("appid", appId);
        requestPayload.put("salt", salt);
        requestPayload.put("sign", sign);
        // invoke
        Call<BaiDuTranslationResponse> call = baiDuApi.translation(requestPayload);
        log.info("{}", call.request());
        Response<BaiDuTranslationResponse> resp = call.execute();
        log.info("{}", resp);
        // handle
        if (!resp.isSuccessful()) {
            log.error("{}", resp.errorBody());
        }
        BaiDuTranslationResponse baiDuTranslationResponse = resp.body();
        if (baiDuTranslationResponse == null) {
            log.warn("baiDuTranslationResponse is null");
            return null;
        }
        log.info("{}", baiDuTranslationResponse);

        if (baiDuTranslationResponse.getError_code() != null) {
            log.warn("translation invoke error : {}", baiDuTranslationResponse.getError_code());
            return baiDuTranslationResponse;
        }

        return baiDuTranslationResponse;
    }
}
