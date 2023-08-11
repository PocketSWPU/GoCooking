package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pocket
 * @date 2023-7-16 22:06:48
 */
@Service
public interface IngredientService {
    /**
     * 根据名字查询配料信息
     * @param name
     * @return
     */
    List<Ingredient> selectByName(String name);

    /**
     * 添加配料信息
     * @param ingredient
     * @return
     */
    Integer insertIngredient(Ingredient ingredient);

    /**
     * 根据dishId和category查询对应的配料
     * @param category
     * @param dishId
     * @return
     */
    List<Ingredient> selectByCategory(Integer category, Integer dishId);
}
