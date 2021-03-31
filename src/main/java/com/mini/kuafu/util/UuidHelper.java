package com.mini.kuafu.util;

import java.util.UUID;

/**
 * @author kenzo
 * @create 2021-02-24 14:47
 */
public final class UuidHelper {
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
