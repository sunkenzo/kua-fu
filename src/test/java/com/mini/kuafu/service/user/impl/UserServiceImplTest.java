package com.mini.kuafu.service.user.impl;

import com.mini.kuafu.KuaFuApplication;
import com.mini.kuafu.service.user.domain.User;
import com.mini.kuafu.service_client.UserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author kenzo
 * @create 2021-02-24 11:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {KuaFuApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setBirthday(LocalDate.now());
        userService.save(user);
    }

    @Test
    public void testGetUser() {
        User user = userService.getById(1357571866516520962L);
        log.info("{}", user);
    }

    @Test
    public void test() {
        log.info("{}", RandomStringUtils.randomAlphanumeric(4));
    }
}