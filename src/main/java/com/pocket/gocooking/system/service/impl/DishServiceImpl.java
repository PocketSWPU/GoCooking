package com.pocket.gocooking.system.service.impl;

import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.mapper.DishMapper;
import com.pocket.gocooking.system.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public Dish selectById(Integer id) {
        return dishMapper.selectById(id);
    }

    @Override
    public List<Dish> selectAll() {
        return dishMapper.selectAll();
    }
}
