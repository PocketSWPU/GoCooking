package com.pocket.gocooking.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.mapper.DishMapper;
import com.pocket.gocooking.system.mapper.UserMapper;
import com.pocket.gocooking.system.service.DishService;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private RedisTemplate<String, Object> redis;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Dish selectById(Integer id) {
        return dishMapper.selectById(id);
    }

    @Override
    public List<Dish> selectAll(String name, String[] difficulty) {
        return dishMapper.selectAll(name, difficulty);
    }

    @Override
    public List<DishIngredientDTO> getIngredientById(Integer id) {
        return dishMapper.getIngredientById(id);
    }

    @Override
    public void addAllTodo(Integer id, String session) {
        // 获取用户session
        Object session1 = redis.opsForValue().get(session);
        User user = JSON.parseObject(JSON.toJSONString(session1),User.class);
        Integer userId = user.getId();

        // 加入Redis
        List<DishIngredientDTO> data = dishMapper.getIngredientById(id);
        String redisKey = "user:"+userId +":todo";
        for (DishIngredientDTO dish : data) {
            redis.opsForSet().add(redisKey, dish.getName());
        }
        redis.expire(redisKey,12, TimeUnit.HOURS);
    }


    @Override
    public Set getAllTodo() {
        return redis.opsForSet().members("todo");
    }


}
