package com.mini.kuafu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectMapper 单例
 *
 * @author kenzo
 * @create 2022-01-04 15:10
 */
public class JsonMapper {

    private static final JsonMapper instance = new JsonMapper();

    private final ObjectMapper objectMapper;


    private JsonMapper() {
        this.objectMapper = new ObjectMapper();

        // 忽略json字符串中不识别的字段
        this.objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 对空串属性赋值null
        this.objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonMapper getInstance() {
        return instance;
    }

    public String writeValueAsString(Object value) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public <T> T readValue(String content, Class<T> valueType) {
        T t = null;
        try {
            t = objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return t;
    }

}
