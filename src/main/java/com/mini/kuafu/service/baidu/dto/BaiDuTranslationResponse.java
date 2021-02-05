package com.mini.kuafu.service.baidu.dto;

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
    private List<TransResult> trans_result;
    private String error_code;
}
