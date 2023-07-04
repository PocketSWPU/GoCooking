package com.pocket.gocooking.system.controller;

import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Pocket
 */
@RestController
@RequestMapping("/todo")
@Tag(name = "Todo", description = "Todo操作")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/getAll")
    @Operation(summary = "获取todo")
    public Result<Set> getAllTodo(@Param("session") String session){
        return Result.success(todoService.getAllTodo(session));
    }

    @DeleteMapping("/clearAll")
    @Operation(summary = "清空todo")
    public Result<Set> clearAll(@Param("session") String session){
        todoService.clearAll(session);
        return Result.success();
    }
}
