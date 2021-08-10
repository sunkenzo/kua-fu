package com.mini.kuafu.common.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.kuafu.KuaFuApplication;
import com.mini.kuafu.domain.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;
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
        stringRedisTemplate.opsForValue().set("test", "100", 60 * 10, TimeUnit.SECONDS);
        // 根据key获取缓存中的val
        stringRedisTemplate.opsForValue().get("test");
        // val -1操作
        stringRedisTemplate.boundValueOps("test").increment(-1);
        // val +1操作
        stringRedisTemplate.boundValueOps("test").increment(1);

        // 向指定key中存放set集合
        stringRedisTemplate.opsForSet().add("red_123", "1", "2", "3");
        // 根据key获取set集合
        stringRedisTemplate.opsForSet().members("red_123");
        // 根据key查看集合中是否存在指定数据
        stringRedisTemplate.opsForSet().isMember("red_123", "1");

        // 根据key删除缓存
        stringRedisTemplate.delete("test");
        // 检查key是否存在，返回boolean值
        stringRedisTemplate.hasKey("546545");

        // 设置过期时间
        stringRedisTemplate.expire("red_123", 1000, TimeUnit.MILLISECONDS);
        // 根据key获取过期时间
        stringRedisTemplate.getExpire("test");
        // 根据key获取过期时间并换算成指定单位
        stringRedisTemplate.getExpire("test", TimeUnit.SECONDS);
    }

    @Test
    public void testStringRedis4Hash() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        //stringRedisTemplate.opsForHash().put("key1", "hashKey1", "value1");
        //stringRedisTemplate.opsForHash().put("key1", "hashKey1", "value2");

        //stringRedisTemplate.opsForHash().put("key1", "hashKey2", objectMapper.writeValueAsString(new UserEntity("name")));

        /*Object obj = stringRedisTemplate.opsForHash().get("key1", "hashKey2");
        if (obj instanceof String) {
            String json = (String) obj;
            UserEntity userEntity = objectMapper.readValue(json, UserEntity.class);
            log.info("{}", userEntity);
        }*/

        //stringRedisTemplate.opsForHash().delete("key1", "hashKey1");

        /*Cursor<Map.Entry<Object, Object>> cursor = stringRedisTemplate.opsForHash()
                .scan("IOT-HUB-25CB5A24", ScanOptions.scanOptions().match("IOT-DTU-*").build());
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }*/

        //stringRedisTemplate.delete("facility.category.safety");
    }
}