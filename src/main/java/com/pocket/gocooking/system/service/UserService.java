package com.pocket.gocooking.system.service;

import com.pocket.gocooking.system.entity.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);
}
