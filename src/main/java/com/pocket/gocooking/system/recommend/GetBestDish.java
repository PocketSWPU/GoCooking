package com.pocket.gocooking.system.recommend;

import com.pocket.gocooking.system.entity.Dish;

/**
 * @author Pocket
 */
public interface GetBestDish {
    public Dish getDish(Integer curUser);
}
