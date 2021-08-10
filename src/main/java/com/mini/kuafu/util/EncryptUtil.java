package com.mini.kuafu.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符脱敏工具类
 *
 * @author kenzo
 * @create 2021-03-31 10:36
 */
public final class EncryptUtil {

    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() < 11) {
            return mobile;
        }

        //return StringUtils.replaceAll(mobile, "(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
