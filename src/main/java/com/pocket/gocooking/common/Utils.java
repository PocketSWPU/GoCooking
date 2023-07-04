package com.pocket.gocooking.common;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Pocket
 */
@Component
public class Utils {

    @Autowired
    private RedisTemplate<String, Object> redis;

    /**
     * 返回User id
     * @param session 登录session
     * @return 对应用户的id
     */
    public Integer getUserId(String session){
        Object value = redis.opsForValue().get(session);
        User user = JSON.parseObject(JSON.toJSONString(value),User.class);
        return user.getId();
    }
}
