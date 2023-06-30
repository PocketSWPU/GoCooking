package com.pocket.gocooking.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.mapper.UserMapper;
import com.pocket.gocooking.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param user 用户信息，包括用户名和密码
     * @return 保存token的简直对, value是用户对象
     */
    @Override
    public Map<String, Object> login(User user) {
        User loginUser = userMapper.selectByNameAndPassword(user);
        if(loginUser != null){
            String key = "user:" + UUID.randomUUID();

            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key, loginUser, 1, TimeUnit.HOURS);
            HashMap<String, Object> data = new HashMap<>(3);
            data.put("token", key);
            return data;
        }
        return null;
    }

    /**
     * 根据token查询信息
     * @param token Redis中保存的token
     * @return 返回保存用户名的HashMap
     */
    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 从redis查询token
        Object obj = redisTemplate.opsForValue().get(token);
        // 反序列化
        User user = JSON.parseObject(JSON.toJSONString(obj),User.class);
        if(user != null){
            Map<String, Object> data =  new HashMap<>();
            data.put("name",user.getUsername());
//            List<String> roleList = this.getBaseMapper().getRoleNamesByUserId(user.getId());
//            data.put("roles", roleList);
            return data;
        }
        return null;
    }

    /**
     * 注销登录，删除redis中的token
     * @param token
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

}
