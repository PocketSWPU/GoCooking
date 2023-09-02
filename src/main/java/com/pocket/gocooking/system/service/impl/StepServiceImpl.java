package com.pocket.gocooking.system.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocket.gocooking.system.entity.Step;
import com.pocket.gocooking.system.mapper.StepMapper;
import com.pocket.gocooking.system.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Pocket
 */
@Service
public class StepServiceImpl implements StepService {
    @Autowired
    private StepMapper stepMapper;
    @Autowired
    private RedisTemplate<String, Object> redis;

    @Override
    public List<Step> selectStepsByDishId(Integer dishId) {
        List<Step> stepList;
        String key = "dish:step:" + dishId;


        // 缓存未命中
        if (!redis.hasKey(key)) {
            // 走db
            stepList = stepMapper.selectStepsByDishId(dishId);
            // 添加缓存
            if (stepList.size() > 0){
                ArrayList<Step> steps = new ArrayList<>(stepList);
                for (Step step: steps){
                    redis.opsForList().rightPush(key, step);
                }
                redis.expire(key, 1, TimeUnit.HOURS);
            }
        } else {
            List<Object> range = redis.opsForList().range(key, 0, -1);
            ObjectMapper mapper = new ObjectMapper();
            stepList = mapper.convertValue(range, new TypeReference<List<Step>>() {
            });
        }
        return stepList;
    }

    @Override
    public Integer insertStepsByDishId(String step, Integer dishId) {
        // 分割
        String[] steps = step.split("\n");
//        int stepCount = 1;
        int stepCount = stepMapper.selectStepsByDishId(dishId).size();
        var list = new ArrayList<Step>();
        for (String s : steps) {
            Step tempStep = new Step(null, dishId, stepCount++, s);
            list.add(tempStep);
        }
        String key = "dish:step:" + dishId;
        Integer integer = stepMapper.insertStepsByDishId(list);
        redis.delete(key);
        return integer;
    }

    @Override
    public Integer deleteSteps(Integer dishId) {
        String key = "dish:step:" + dishId;
        redis.delete(key);
        return stepMapper.deleteStepsByDishId(dishId);
    }
}
