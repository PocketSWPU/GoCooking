package com.pocket.gocooking.system.service;


import java.util.Set;

public interface TodoService {
    /**
     * 根据用户session获取id，然后获取该用户的todo
     * @param session session
     * @return 包含todo的集合
     */
    Set getAllTodo(String session);

    /**
     * 根据session删除对应用户的todo
     * @param session
     */
    void clearAll(String session);
}
