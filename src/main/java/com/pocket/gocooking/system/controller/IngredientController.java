package com.pocket.gocooking.system.controller;

import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.Ingredient;
import com.pocket.gocooking.system.service.DishService;
import com.pocket.gocooking.system.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/ingredient")
@Tag(name = "配料", description = "配料操作")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private DishService dishService;

    @GetMapping("/selectByIdAndCategory/{dishId}/{category}")
    @Operation(description = "查询配料")
    public Result selectByCategory(@PathVariable("dishId") Integer dishId, @PathVariable("category") Integer category){
        List<Ingredient> ingredients = ingredientService.selectByCategory(category, dishId);
        return Result.success(ingredients);
    }

    @PostMapping("/updateDishIngredient/{id}/{category}")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "更新关联材料")
    public Result updateDishIngredient(@PathVariable("id") Integer id, @RequestParam(value = "str") String str,
                                       @PathVariable("category") Integer category){
        dishService.updateDishIngredient(id, str, category);
        return Result.success();
    }
}
