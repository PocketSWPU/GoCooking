package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface DishMapper extends BaseMapper<Dish> {
    @Select("select dish_id, name, time, difficulty from dish_table where dish_id = #{dish_id}")
    Dish selectById(@Param("dish_id") Integer id);

    @Select("select * from dish_table")
    List<Dish> selectAll();
}
