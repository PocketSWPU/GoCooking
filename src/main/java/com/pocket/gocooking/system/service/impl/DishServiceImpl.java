package com.pocket.gocooking.system.service.impl;

import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.mapper.DishMapper;
import com.pocket.gocooking.system.service.DishService;

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
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

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
}
