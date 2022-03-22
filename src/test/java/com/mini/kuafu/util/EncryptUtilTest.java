package com.mini.kuafu.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author kenzo
 * @create 2021-03-31 10:40
 */
public class EncryptUtilTest {
    private final Logger logger = LoggerFactory.getLogger(EncryptUtilTest.class);

    @Test
    public void mobilEncrypt() {
        logger.info("{}", EncryptUtil.mobileEncrypt("18201434735"));
    }

    @Test
    public void testDuration() {
        LocalDateTime startTime = TimeHelper.parseDateTime("2021-06-23 00:00:00");
        LocalDateTime endTime = TimeHelper.parseDateTime("2021-08-23 00:00:00");

        logger.info("days equals: " + startTime.equals(endTime));

        long days = Duration.between(startTime, endTime).toDays();
        System.out.println("days: " + days);
        logger.info("days: {}", days);
    }
}