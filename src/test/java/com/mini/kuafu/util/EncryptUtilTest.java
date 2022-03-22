package com.mini.kuafu.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

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
}