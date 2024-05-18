package com.syr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        String currentWorkingDir = System.getProperty("user.dir");
        currentWorkingDir = currentWorkingDir.substring(0, currentWorkingDir.lastIndexOf("\\"));
        System.out.println(currentWorkingDir);
    }
}
