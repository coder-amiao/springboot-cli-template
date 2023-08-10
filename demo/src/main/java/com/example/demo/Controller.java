package com.example.demo;

import cn.soboys.simplest.cache.RedisTempUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenx
 * @version 1.0
 * @date 2023/8/10 09:48
 */
@RestController
public class Controller {

    @Autowired
    RedisTempUtil redisTempUtil;

    @GetMapping("/test")
    public String ts(){
       redisTempUtil.set("test",111);
        return "hello";
    }
}
