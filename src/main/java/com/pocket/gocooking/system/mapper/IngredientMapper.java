package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.Ingredient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@Mapper
public interface IngredientMapper{
    /**
     * 根据名字查询
     * @param name
     * @return
     */
    List<Ingredient> selectIngredient(@Param("name") String name,
                                      @Param("ingredientId") Integer ingredientId,
                                      @Param("categoryId") Integer categoryId);

    /**
     * mapper接口重载测试
     * @param name
     * @return
     */
    List<Ingredient> selectIngredient(@Param("name") String name);

    /**
     * 添加配料
     * @param ingredient 配料
     * @return
     */
    Integer insertIngredient(Ingredient ingredient);

    /**
     * 根据dishId查询对应的category的配料名
     * @param category
     * @param dishId
     * @return
     */
    List<Ingredient> selectIngredientsByCategory(@Param("category") Integer category, @Param("dishId") Integer dishId);
}
