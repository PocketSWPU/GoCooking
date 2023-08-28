package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.DishIngredient;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface DishMapper{
    @Select("select dish_id as dishId, name, difficulty from dish_table where dish_id = #{dish_id}")
    Dish selectById(@Param("dish_id") Integer id);

    /**
     * 通过菜名or困难度筛选
     * @param name
     * @param difficulty
     * @return
     */
    List<Dish> selectAll(@Param("name") String name, @Param("difficulty") String[] difficulty,
                         @Param("creatId") Integer creatId);

    /**
     * 获取和dish_id对应的
     * @param dishId
     * @return
     */
    List<DishIngredientDTO> getIngredientById(@Param("dishId") Integer dishId);

    /**
     * 插入数据
     * @param dish 菜品
     * @return id
     */
    Integer insertDish(Dish dish);

    /**
     * 加入菜品、配料联系表
     * @param dishIngredient
     */
    void insertDishIngredient(DishIngredient dishIngredient);
//    boolean selectByName(@Param("name"));

    Integer deleteDishById(Integer dishId);
}
