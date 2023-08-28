package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.DishIngredient;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Pocket
 */
@Service
public interface DishService {
    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    Dish selectById(Integer id);

    List<Dish> selectAll(String name, String[] difficulty, Integer userId);

    List<DishIngredientDTO> getIngredientById(Integer id);

    void addAllTodo(Integer id, String session);

    Set getAllTodo();

    /**
     * 新增一个菜品
     * @param name 菜名
     * @param difficulty 难易度
     * @param ingredients 先用'|'分割，再以逗号分割的字符串
     * @param userId 用户id
     * @return
     * -1: 有重复
     */
    Integer insertDish(String name, String difficulty, String ingredients, Integer userId);

    /**
     * 删除菜品
     * @param id 主键
     * @return 操作数
     */
    Integer deleteDish(Integer id);

    /**
     * 更新关联材料
     * @param id
     * @param ingredientStr
     * @return
     */
    Integer updateDishIngredient(Integer id, String ingredientStr, Integer category);

    /**
     * 查询用户做某个菜的次数
     * @param dishId
     * @param session
     * @return
     */
    Integer getTimes(Integer dishId, String session);

    /**
     * 增加次数
     * @param dishId
     * @param session
     * @return
     */
    Integer increaseTimes(Integer dishId, String session);

    /**
     * 获取排行榜
     * @param session
     * @return
     */
    List<HashMap<String,String>> getRankingList(String session);
}
