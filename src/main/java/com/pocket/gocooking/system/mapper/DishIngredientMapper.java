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

    /**
     * 删除与dishId相关的种类为categoryId的配料关联
     * @param dishId
     * @param categoryId
     * @return
     */
    Integer deleteByCategory(Integer dishId, Integer categoryId);
}
