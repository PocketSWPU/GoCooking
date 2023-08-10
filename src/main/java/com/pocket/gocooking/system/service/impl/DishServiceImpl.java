package com.pocket.gocooking.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.pocket.gocooking.system.entity.*;
import com.pocket.gocooking.system.mapper.DishIngredientMapper;
import com.pocket.gocooking.system.mapper.DishMapper;
import com.pocket.gocooking.system.mapper.IngredientMapper;
import com.pocket.gocooking.system.mapper.UserMapper;
import com.pocket.gocooking.system.service.DishService;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Integer insertDish(String name, String difficulty, String ingredients) {
        // 1. 查询名字是否重复，重复返回错误
        if(dishMapper.selectAll(name,null).size() != 0){
            return -1;
        }

        // 2. 插入
        // dish_table: name, difficulty; 返回dish_id
        // ingredient_table: category, name; 返回ingredient_id
        // dish_ingredient_table: dish_id, ingredient_id
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDifficulty(difficulty);
        dishMapper.insertDish(dish);
        Integer dishId = dish.getDishId();

        // 3. 分离字符串
        // 3.1 插入ingredient_table
        // 3.2 如果数据库里没有, 先添加   ===  这里可以加缓存
        int categoryCount = 0;
        String[] ingredientsGroup = ingredients.split("x");
        for(String ingredient: ingredientsGroup){
            ++categoryCount;
            if("undefined".equals(ingredient)){
                continue;
            }
            String[] tempIngredients = ingredient.replaceAll(",","，").split("，");
            for(String ingredientName: tempIngredients){
                // 不存在 添加
                Integer ingredientAdded = -1;
//                Ingredient tempIngre = ingredientMapper.selectByName(ingredientName);
                List<Ingredient> tempIngreList = ingredientMapper.selectByName(ingredientName);

                // 看配料存不存在
                if(tempIngreList.size() < 1){
                    Ingredient ingredient1 = new Ingredient();
                    ingredient1.setIngredientName(ingredientName);
                    ingredient1.setCategoryId(categoryCount);
                    ingredientMapper.insertIngredient(ingredient1);
                    ingredientAdded = ingredient1.getIngredientId();
                }else{
                    ingredientAdded = tempIngreList.get(0).getIngredientId();
                }

                // 存在 添加到dish_ingredient_table
                DishIngredient dishIngredient = new DishIngredient();
                dishIngredient.setDishId(dishId);
                dishIngredient.setIngredientId(ingredientAdded);
                dishMapper.insertDishIngredient(dishIngredient);
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
}
