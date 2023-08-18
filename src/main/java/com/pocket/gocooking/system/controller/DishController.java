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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

//    @Autowired
//    private CacheManager cacheManager;

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
        if(name != null) {
            // 加上通配符
            name = "%" + name +"%";
        }
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

    @GetMapping("/todo")
    @Operation(summary = "获取todo")
    public Result<Set> getAllTodo(){
        return Result.success(dishService.getAllTodo());
    }

    @PutMapping("/addAll")
    public Result addAllTodo(@Param("id") Integer id, @Param("session") String session){
        dishService.addAllTodo(id, session);
        return Result.success();
    }

    @PutMapping("/insert")
    @Operation(summary = "添加菜品")
    @Transactional(rollbackFor = Exception.class)
    public Result insertDish(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "difficulty", required = false) String difficulty,
                             @RequestParam(value = "ingredients", required = false) String ingredients){
        Integer result = dishService.insertDish(name, difficulty, ingredients);

        return result == 1?Result.success():Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "删除菜品")
    public Result deleteDish(@PathVariable("id") Integer id){
        dishService.deleteDish(id);
        return Result.success();
    }


}
