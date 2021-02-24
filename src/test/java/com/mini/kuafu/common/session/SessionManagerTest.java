package com.mini.kuafu.common.session;

import com.mini.kuafu.KuaFuApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author kenzo
 * @create 2021-02-23 17:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KuaFuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionManagerTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testStringRedisTemplate() throws InterruptedException {
        // 向redis里存入数据和设置缓存时间
        stringRedisTemplate.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);
        // 根据key获取缓存中的val
        stringRedisTemplate.opsForValue().get("test");
        // val -1操作
        stringRedisTemplate.boundValueOps("test").increment(-1);
        // val +1操作
        stringRedisTemplate.boundValueOps("test").increment(1);

        // 向指定key中存放set集合
        stringRedisTemplate.opsForSet().add("red_123", "1","2","3");
        // 根据key获取set集合
        stringRedisTemplate.opsForSet().members("red_123");
        // 根据key查看集合中是否存在指定数据
        stringRedisTemplate.opsForSet().isMember("red_123", "1");

        // 根据key删除缓存
        stringRedisTemplate.delete("test");
        // 检查key是否存在，返回boolean值
        stringRedisTemplate.hasKey("546545");

        // 设置过期时间
        stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);
        // 根据key获取过期时间
        stringRedisTemplate.getExpire("test");
        // 根据key获取过期时间并换算成指定单位
        stringRedisTemplate.getExpire("test", TimeUnit.SECONDS);
    }

}