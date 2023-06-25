package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {
    Dish selectById(Integer id);

    List<Dish> selectAll();
}
