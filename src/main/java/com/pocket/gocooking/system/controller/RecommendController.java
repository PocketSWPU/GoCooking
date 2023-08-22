package com.pocket.gocooking.system.controller;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.recommend.RecommendServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pocket
 */
@RestController
@RequestMapping("/recommend")
@Tag(name = "推荐", description = "推荐接口")
public class RecommendController {

    @Autowired
    private RedisTemplate<String, Object> redis;
    @Autowired
    RecommendServiceImpl recommendService;

    @GetMapping("/generate")
    public Result<List<Dish>> getRecommendation(@Param("session") String session){
        Object obj = redis.opsForValue().get(session);
        User user = JSON.parseObject(JSON.toJSONString(obj),User.class);
        List<Dish> dishes = recommendService.initRecommendation(user);
        return Result.success(dishes);
    }
}
