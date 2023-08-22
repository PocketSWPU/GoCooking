package com.pocket.gocooking.system.recommend;

import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 随机获取一个菜品来推荐
 * 初期测试demo用
 * @author Pocket
 */
@Service
public class GetByRandom implements GetBestDish {

    @Autowired
    DishMapper dishMapper;
    @Override

    public Dish getDish(Integer curUser) {
        List<Dish> dishes = dishMapper.selectAll(null, null, curUser);
        int randomIndex = new Random().nextInt(dishes.size());
        return dishes.get(randomIndex);
    }
}
