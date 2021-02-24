package com.mini.kuafu.common.logger;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

/**
 * 日志过滤器
 *
 * @author kenzo
 * @create 2021-02-05 18:35
 */
@Component
@Priority(Integer.MAX_VALUE - 2)
public class LoggerFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        Instant start = Instant.now();
        filterChain.doFilter(requestWrapper, responseWrapper);
        Instant end = Instant.now();

        String requestBody = readRequest(requestWrapper);

        String responseBody;
        if (!Strings.isNullOrEmpty(responseWrapper.getContentType()) &&
                // 下载类的返回不打印到控制台.
                responseWrapper.getContentType().contains("stream")) {
            responseBody = "<<binary>>";
        } else if (request.getRequestURI().contains("/basic/geo-list")) {
            responseBody = "<<Full Geo List>>";
        } else if (request.getRequestURI().contains("/basic/qrcode")) {
            responseBody = "<<qrcode image>>";
        } else {
            responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        }

        responseWrapper.copyBodyToResponse();

        logger.info("{} {} {}\n<<<<<<<<<<\nq= {}\nt= {} ms\nr= {}\n>>>>>>>>>>\n",
                request.getMethod(),
                request.getRequestURI(),
                responseWrapper.getStatus(),
                requestBody,
                Duration.between(start, end).toMillis(),
                responseBody
        );
    }

    private String readRequest(ContentCachingRequestWrapper request) {
        if ("GET".equals(request.getMethod())) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
                sb.append(e.getKey()).append(":").append(Arrays.toString(e.getValue())).append(", ");
            }

            return sb.toString();
        } else if ("POST".equals(request.getMethod())) {
            return new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
        } else {
            return request.getMethod();
        }
    }
}
