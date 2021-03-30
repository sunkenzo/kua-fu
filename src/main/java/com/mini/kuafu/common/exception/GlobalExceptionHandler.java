package com.mini.kuafu.common.exception;

import com.mini.kuafu.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kenzo
 * @create 2021-02-24 17:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BadSqlGrammarException.class)
    public ApiResult badSqlGrammarExceptionHandle(BadSqlGrammarException e) {
        log.error(e.getMessage(), e);

        return ApiResult.error("");
    }
}
