package com.pocket.gocooking.system.recommend;

import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendService {
    List<Dish> initRecommendation(User curUser);
}
