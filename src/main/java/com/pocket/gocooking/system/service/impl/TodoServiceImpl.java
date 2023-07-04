package com.pocket.gocooking.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.common.Utils;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 *
 * @author Pocket
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private RedisTemplate<String, Object> redis;

    @Autowired
    private Utils utils;


    @Override
    public Set getAllTodo(String session) {
        String userKey = "user:" + utils.getUserId(session) + ":todo";
        return redis.opsForSet().members(userKey);
    }

    @Override
    public void clearAll(String session) {
        String key = "user:" + utils.getUserId(session) + ":todo";
        redis.delete(key);
    }
}
