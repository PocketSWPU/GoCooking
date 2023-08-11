package com.pocket.gocooking.system.controller;

import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.Ingredient;
import com.pocket.gocooking.system.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/ingredient")
@Tag(name = "配料", description = "配料操作")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/selectByIdAndCategory/{dishId}/{category}")
    @Operation(description = "查询配料")
    public Result selectByCategory(@PathVariable("dishId") Integer dishId, @PathVariable("category") Integer category){
        List<Ingredient> ingredients = ingredientService.selectByCategory(category, dishId);
        return Result.success(ingredients);
    }
}
