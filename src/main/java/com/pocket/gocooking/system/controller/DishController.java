package com.pocket.gocooking.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.common.Utils;
import com.pocket.gocooking.system.entity.DishIngredientDTO;
import com.pocket.gocooking.system.entity.Dish;
import com.pocket.gocooking.system.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
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

    @Autowired
    private Utils utils;

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
                                                 @RequestParam(value = "difficulty", required = false) String[] difficulty,
                                                 @RequestParam("cookie") String cookie) {
        if(name != null) {
            // 加上通配符
            name = "%" + name +"%";
        }
        Integer userId = utils.getUserId(cookie);
//        String[] difficultyArray = difficulty.split(",");
        PageHelper.startPage(pageNo, pageSize);

        List<Dish> dishResult = dishService.selectAll(name,difficulty,userId);
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
                             @RequestParam(value = "ingredients", required = false) String ingredients,
                             @RequestParam(value = "cookie") String cookie){
        Integer userId = utils.getUser(cookie);
        Integer result = dishService.insertDish(name, difficulty, ingredients, userId);

        return result == 1?Result.success():Result.fail();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "删除菜品")
    public Result deleteDish(@PathVariable("id") Integer id){
        dishService.deleteDish(id);
        return Result.success();
    }

    @GetMapping("/times")
    @Operation(summary = "获取次数")
    public Result getTimes(@Param("dishId") Integer dishId, @Param("session") String session){
        Integer times = dishService.getTimes(dishId, session);
        return Result.success(times);
    }

    @PostMapping("/times/incr")
    @Operation(summary = "增加次数")
    public Result incrTimes(@Param("dishId") Integer dishId, @Param("session") String session){
        Integer ans = dishService.increaseTimes(dishId, session);
        return Result.success(ans);
    }
    @GetMapping("/ranking")
    @Operation(summary = "排行")
    public Result getRankingList(@Param("session") String session){
        List<HashMap<String,String>> rankingList = dishService.getRankingList(session);
        return Result.success(rankingList);
    }

}
