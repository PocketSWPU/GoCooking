package com.pocket.gocooking.system.recommend;

import com.pocket.gocooking.common.Utils;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.entity.User;
import com.pocket.gocooking.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Pocket
 */
@Service
public class RecommendServiceImpl implements RecommendService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    Utils utils;
    @Autowired
    GetBestDish getBestDish;

    static class UserPack{
        Integer uid;
        Double sim;

        public UserPack(Integer uid, Double sim) {
            this.uid = uid;
            this.sim = sim;
        }
    }

    @Override
    public List<Dish> initRecommendation(User curUser){
        // 用户id集合
        List<User> userList = userMapper.selectAllUsers();
        PriorityQueue<UserPack> heap = new PriorityQueue<>((a,b) -> b.sim.equals(a.sim) ? 0 : a.sim > b.sim ? 1:-1);
        for (User u: userList){
            // 跳过自己
            if(!u.getId().equals(curUser.getId())){
                double similarity = getSimilarity(curUser, u);
                if (heap.size() > 0 && similarity > heap.peek().sim){
                    heap.add(new UserPack(u.getId(), similarity));
                    heap.remove();
                }else if(heap.size() < 10){
                    heap.add(new UserPack(u.getId(), similarity));
                }
            }
        }
        List<Dish> recommendList = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        // 应该set加入curUser的菜品名，防重复
        // 待处理！
        while (heap.size() > 0){
            Integer candidateId = heap.remove().uid;
            // 获取这个用户做的次数最多的 (最喜欢的 / 最早添加的)
            Dish dish = getBestDish.getDish(candidateId);
            if(!set.contains(dish.getName())){
                recommendList.add(dish);
                set.add(dish.getName());
            }
        }
        Collections.reverse(recommendList);
        return recommendList;
    }

    /**
     * Jaccard + 加权
     * @param u1
     * @param u2
     * @return
     */
    private double getSimilarity(User u1, User u2){
        return   u1.getAge().equals(u2.getAge()) ? 0.3 : 0
                + (u1.getGender().equals(u2.getGender()) ? 0.2 : 0)
                + (u1.getProvince().equals(u2.getProvince()) ? 0.4 : 0);
    }
}
