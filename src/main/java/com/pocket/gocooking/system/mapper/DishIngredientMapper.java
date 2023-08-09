package com.pocket.gocooking.system.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Pocket
 */
@Mapper
public interface DishIngredientMapper {
    /**
     * 根据dishID删除
     * @param dishId
     * @return
     */
    Integer deleteIngredientByDishId(Integer dishId);
}
