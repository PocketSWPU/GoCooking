package com.pocket.gocooking.system.controller;

import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.service.IDishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@RestController
@RequestMapping("/dish")
@Tag(name = "菜单", description = "菜单操作")
public class DishController {

    @Autowired
    private IDishService dishService;

    @GetMapping("/hello")
    @Operation(summary = "测试接口")
    public String index(@Parameter(name = "name", description = "名称") String name) {
        return "hello " + name;
    }

    @GetMapping("/test")
    @Operation(summary = "测试接口")
    public String test() {
        return "good night";
    }

    @GetMapping("/select/{id}")
    @Operation(summary = "根据id查询")
    public Dish selectById(@PathVariable("id") Integer id){
        return dishService.selectById(id);
    }

    public List<Dish> selectAll(){
        return dishService.selectAll();
    }
}
