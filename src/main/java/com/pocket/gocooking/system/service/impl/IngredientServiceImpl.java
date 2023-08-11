package com.pocket.gocooking.system.service.impl;

import com.pocket.gocooking.system.entity.Ingredient;
import com.pocket.gocooking.system.mapper.IngredientMapper;
import com.pocket.gocooking.system.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientMapper ingredientMapper;

    @Override
    public List<Ingredient> selectByName(String name) {
        return ingredientMapper.selectIngredient(name);
    }

    @Override
    public Integer insertIngredient(Ingredient ingredient) {
        return ingredientMapper.insertIngredient(ingredient);
    }

    @Override
    public List<Ingredient> selectByCategory(Integer category, Integer dishId) {
        return ingredientMapper.selectIngredientsByCategory(category,dishId);
    }
}
