package com.mini.kuafu.common.response;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kenzo
 * @create 2021-02-24 17:49
 */
@Setter
@Getter
public class ApiResult<T> {
    private final static Integer CODE_OK = 200;
    private final static String MESSAGE_OK = "OK";
    private static final Integer CODE_ERROR = 500;
    private static final ApiResult<String> OK = new ApiResult<>(CODE_OK, MESSAGE_OK);

    private Integer code;
    private String message;
    private T data;

    private ApiResult() {

    }

    public ApiResult(T data) {
        this.code = CODE_OK;
        this.message = MESSAGE_OK;
        this.data = data;
    }

    private ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    public static ApiResult<String> ok() {
        return OK;
    }

    public static ApiResult<String> okWithMessage(String message) {
        ApiResult<String> result = new ApiResult<>();
        result.setMessage(message);
        return result;
    }

    public static ApiResult<Object> okWithData(Object data) {
        ApiResult<Object> result = new ApiResult<>();
        result.setData(data);
        return result;
    }

    public static ApiResult<Object> ok(Object data) {
        return okWithData(data);
    }

    public static ApiResult<Object> okWithMessageAndData(String message, Object data) {
        ApiResult<Object> result = new ApiResult<>();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ApiResult<String> error(String message) {
        return new ApiResult<>(CODE_ERROR, message, null);
    }

    public static ApiResult<String> error(Integer code, String message) {
        return new ApiResult<>(code, message, null);
    }

    public static ApiResult<Object> error(Integer code, String message, Object data) {
        return new ApiResult<>(code, message, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("data", data)
                .toString();
    }

}
