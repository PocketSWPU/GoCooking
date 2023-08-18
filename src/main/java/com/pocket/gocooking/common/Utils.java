package com.pocket.gocooking.common;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.system.entity.Ingredient;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Pocket
 */
@Component
public class Utils {

    @Autowired
    private RedisTemplate<String, Object> redis;

    @Autowired
    private IngredientMapper ingredientMapper;

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

    /**
     * 传入配料名字和种类，返回对应的id
     * 存在：返回已有的id
     * 不存在：添加后返回id
     * @param ingredientName
     * @param categoryCount
     * @return
     */
    public Integer checkIngredient(String ingredientName, int categoryCount){
        List<Ingredient> tempIngreList = ingredientMapper.selectIngredient(ingredientName,null,null);
        // 不存在 添加
        Integer ingredientAddedId = null;
        if(tempIngreList.size() < 1){
            Ingredient tempIngredient = new Ingredient(null,categoryCount, ingredientName);
            ingredientMapper.insertIngredient(tempIngredient);
            // 获取新增的id
            ingredientAddedId = tempIngredient.getIngredientId();
        }else{
            // 存在，获取id
            ingredientAddedId = tempIngreList.get(0).getIngredientId();
        }
        return ingredientAddedId;
    }
}
