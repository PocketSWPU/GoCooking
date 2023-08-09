package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.DishIngredient;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import org.springframework.stereotype.Service;

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

    List<Dish> selectAll(String name, String[] difficulty);

    List<DishIngredientDTO> getIngredientById(Integer id);

    void addAllTodo(Integer id, String session);

    Set getAllTodo();

    /**
     * 新增一个菜品
     * @param name 菜名
     * @param difficulty 难易度
     * @param ingredients 先用'|'分割，再以逗号分割的字符串
     * @return
     * -1: 有重复
     */
    Integer insertDish(String name, String difficulty, String ingredients);

    /**
     * 删除菜品
     * @param id 主键
     * @return 操作数
     */
    Integer deleteDish(Integer id);
}
