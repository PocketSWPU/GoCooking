package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Pocket
 */
@Mapper
public interface UserMapper {
    User selectByNameAndPassword(User user);

    /**
     * 获取所有用户的集合
     * @return
     */
    List<User> selectAllUsers();

//    Integer selectIdBySession();
}
