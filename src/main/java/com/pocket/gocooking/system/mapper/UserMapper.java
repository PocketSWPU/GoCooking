package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByNameAndPassword(User user);


}
