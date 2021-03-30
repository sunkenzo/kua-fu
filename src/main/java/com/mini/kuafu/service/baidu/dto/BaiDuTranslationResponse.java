package com.mini.kuafu.service.baidu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author kenzo
 * @create 2021-02-05 15:49
 */
@Setter
@Getter
@ToString
public class BaiDuTranslationResponse {
    private String from;
    private String to;
    @JsonProperty("trans_result")
    private List<TransResult> transResult;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("error_msg")
    private String errorMsg;
}
