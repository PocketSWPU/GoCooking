package com.pocket.gocooking.system.controller;

import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.Step;
import com.pocket.gocooking.system.service.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Pocket
 */
@RestController
@RequestMapping("/step")
@Tag(name = "步骤", description = "步骤操作")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping("/select/{dishId}")
    @Operation(description = "查询步骤")
    public Result selectStepsByDishId(@PathVariable("dishId") Integer dishId){
        List<Step> steps = stepService.selectStepsByDishId(dishId);
        return Result.success(steps);
    }

    @PostMapping("/insert")
    @Operation(description = "添加步骤")
    public Result insertStepsByDishId(@Param("description") String description, @Param("dishId") Integer dishId){
        Integer num = stepService.insertStepsByDishId(description, dishId);
        return Result.success(num);
    }

    @DeleteMapping("/clear/{dishId}")
    @Operation(description = "删除步骤")
    public Result clearSteps(@PathVariable("dishId") Integer dishId){
        Integer num = stepService.deleteSteps(dishId);
        return Result.success(num);
    }
}
