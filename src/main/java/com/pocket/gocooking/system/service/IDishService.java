package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
public interface IDishService extends IService<Dish> {
    Dish selectById(Integer id);

    List<Dish> selectAll();

}
