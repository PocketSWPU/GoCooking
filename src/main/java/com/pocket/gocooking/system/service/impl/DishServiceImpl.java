package com.pocket.gocooking.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.common.Utils;
import com.pocket.gocooking.system.entity.*;
import com.pocket.gocooking.system.flyweight.IngredientFlyweight;
import com.pocket.gocooking.system.mapper.DishIngredientMapper;
import com.pocket.gocooking.system.mapper.DishMapper;
import com.pocket.gocooking.system.mapper.IngredientMapper;
import com.pocket.gocooking.system.mapper.UserMapper;
import com.pocket.gocooking.system.service.DishService;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private RedisTemplate<String, Object> redis;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IngredientMapper ingredientMapper;
    @Autowired
    private DishIngredientMapper dishIngredientMapper;
    @Autowired
    private Utils utils;

    @Autowired
    @Qualifier("ingredientFlyweight")
    IngredientFlyweight ingredientPool = new IngredientFlyweight();

    @Override
    public Dish selectById(Integer id) {
        return dishMapper.selectById(id);
    }

    @Override
    public List<Dish> selectAll(String name, String[] difficulty, Integer userId) {
        return dishMapper.selectAll(name, difficulty, userId);
    }

    @Override
    public List<DishIngredientDTO> getIngredientById(Integer id) {
        return dishMapper.getIngredientById(id);
    }

    @Override
    public void addAllTodo(Integer id, String session) {
        // 获取用户session
        Object session1 = redis.opsForValue().get(session);
        User user = JSON.parseObject(JSON.toJSONString(session1), User.class);
        Integer userId = user.getId();

        // 加入Redis
        List<DishIngredientDTO> data = dishMapper.getIngredientById(id);
        String redisKey = "user:" + userId + ":todo";
        for (DishIngredientDTO dish : data) {
            redis.opsForSet().add(redisKey, dish.getName());
        }
        redis.expire(redisKey, 12, TimeUnit.HOURS);
    }


    @Override
    public Set getAllTodo() {
        return redis.opsForSet().members("todo");
    }

    @Override
    public Integer insertDish(String name, String difficulty, String ingredients, Integer userId) {
        // 1. 查询名字是否重复，重复返回错误
        if (dishMapper.selectAll(name, null, userId).size() != 0) {
            return -1;
        }

        // 2. 插入
        // dish_table: name, difficulty; 返回dish_id
        // ingredient_table: category, name; 返回ingredient_id
        // dish_ingredient_table: dish_id, ingredient_id
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDifficulty(difficulty);
        dish.setCreatId(userId);
        dishMapper.insertDish(dish);
        Integer dishId = dish.getDishId();

        // 3. 分离字符串
        // 3.1 插入ingredient_table
        // 3.2 如果数据库里没有, 先添加   ===  这里可以加缓存
        int categoryCount = 0;
        String[] ingredientsGroup = ingredients.split("x");
        for (String ingredient : ingredientsGroup) {
            ++categoryCount;
            if ("undefined".equals(ingredient)) {
                continue;
            }
            String[] tempIngredients = ingredient.replaceAll(",", "，").split("，");
            for (String ingredientName : tempIngredients) {
                if ("".equals(ingredientName)) {
                    continue;
                }
                // 看配料存不存在
                // 先看Redis
                Boolean isInCache = redis.opsForHash().hasKey("ingredientCache", ingredientName);
                Integer ingredientAddedId = -1;
                // 在缓存中
                if (isInCache) {
                    // Hash -> k: name, v: id
                    ingredientAddedId = (Integer) redis.opsForHash().get("ingredientCache", ingredientName);
                } else {
                    // 查db
                    ingredientAddedId = utils.checkIngredient(ingredientName, categoryCount);

                    // 更新缓存，仅新增
                    redis.opsForHash().put("ingredientCache", ingredientName, ingredientAddedId);
                }
                // 添加到dish_ingredient_table
                // new -> 享元模式
                DishIngredient dishIngredient = ingredientPool.getIngredient(ingredientName);
                dishIngredient.setDishId(dishId);
                dishIngredient.setIngredientId(ingredientAddedId);
                dishMapper.insertDishIngredient(dishIngredient);

                // Redis Zset
                redis.opsForZSet().add("uid:ranking", ingredientName, 1);
            }
        }

        return 1;
    }

    @Override
    public Integer deleteDish(Integer dishId) {
        // 1. 删除关联的配料
        dishIngredientMapper.deleteIngredientByDishId(dishId);
        // 2. 删除菜品
        return dishMapper.deleteDishById(dishId);
    }

    @Override
    public Integer updateDishIngredient(Integer dishId, String ingredientStr, Integer category) {
        String[] labels = ingredientStr.replaceAll(",", "，").split("，");
        // 删除全部已经关联的
        dishIngredientMapper.deleteByCategory(dishId, category);
        int ans = 0;
        for (String label : labels) {
            Integer ingredientId = utils.checkIngredient(label, category);
            DishIngredient dishIngredient = new DishIngredient();
            dishIngredient.setDishId(dishId);
            dishIngredient.setIngredientId(ingredientId);
            dishMapper.insertDishIngredient(dishIngredient);
        }
        return 1;
    }

    @Override
    public Integer getTimes(Integer dishId, String session) {
        String key = "uid:" + utils.getUser(session);
        Double score = redis.opsForZSet().score(key, dishId);
        return score == null ? 0 : score.intValue();
    }

    @Override
    public Integer increaseTimes(Integer dishId, String session) {

        String key = "uid:" + utils.getUser(session);
        Double times = 1d;
        if (getTimes(dishId, session) == 0) {
            redis.opsForZSet().add(key, dishId, 1);
        } else {
            times = redis.opsForZSet().incrementScore(key, dishId, 1);
        }
        return times.intValue();
    }

    @Override
    public List<HashMap<String,String>> getRankingList(String session) {
        String key = "uid:" + utils.getUser(session);
        List<HashMap<String,String>> ans = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redis.opsForZSet().reverseRangeWithScores(key, 0, 4);
        if (typedTuples != null) {
            for (ZSetOperations.TypedTuple<Object> typedTuple : typedTuples) {
                Integer dishId = (Integer) typedTuple.getValue();
                Integer score = typedTuple.getScore().intValue();
                String redisCacheKey = "dish:id:" + dishId;
                if (redis.opsForValue().get(redisCacheKey) != null) {
                    HashMap<String,String> json = new HashMap<>();
                    json.put("dishName", (String) redis.opsForValue().get(redisCacheKey));
                    json.put("score", String.valueOf(score));
                    ans.add(json);
                } else{
                    Dish temp = dishMapper.selectById(dishId);
                    redis.opsForValue().set(redisCacheKey, temp.getName());
                    HashMap<String,String> json = new HashMap<>();
                    json.put("dishName", temp.getName());
                    json.put("score", String.valueOf(score));
                    ans.add(json);
                }
            }
        }

        return ans;
    }
}
