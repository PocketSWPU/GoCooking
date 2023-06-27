package com.pocket.gocooking.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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
    private DishService dishService;

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
    public Dish selectById(@PathVariable("id") Integer id) {
        return dishService.selectById(id);
    }

//    @GetMapping("/selectAll")
//    @Operation(summary = "查询所有")
//    public List<Dish> selectAll(){
//        return dishService.selectAll();
//    }

    @GetMapping("/selectAll")
    @Operation(summary = "查询所有")
    public Result<Map<String, Object>> selectAll(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam("pageNo") Integer pageNo,
                                                 @RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam(value = "difficulty", required = false) String[] difficulty) {
        if(name != null) name = "%" + name +"%"; // 加上通配符
//        String[] difficultyArray = difficulty.split(",");
        PageHelper.startPage(pageNo, pageSize);
        List<Dish> dishResult = dishService.selectAll(name,difficulty);
        PageInfo<Dish> pageInfo = new PageInfo<>(dishResult);
        System.out.println(pageInfo);
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", pageInfo.getList());
        data.put("total", pageInfo.getTotal());

        return Result.success(data);
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过id查询配料")
    public Result<List<DishIngredientDTO>> getIngredientById(@Param("id") Integer id){
        return Result.success(dishService.getIngredientById(id));
    }
}
