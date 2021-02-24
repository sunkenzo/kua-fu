package com.mini.kuafu.service.auth.impl;

import com.mini.kuafu.KuaFuApplication;
import com.mini.kuafu.service_client.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author kenzo
 * @create 2021-02-24 15:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KuaFuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AuthService authService;

    @Test
    public void testLogin() {
        String result = authService.login("a", "1234");
        log.info("{}", result);
    }

    @Test
    public void testLogout() {
        authService.logout("oT2Q4ZV3fe18fca1f0624b3a86a53b19dbc60c6e#1357571866516520962");
    }
}