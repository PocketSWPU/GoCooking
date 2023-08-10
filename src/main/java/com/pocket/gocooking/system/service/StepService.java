package com.pocket.gocooking.system.service;

import com.pocket.gocooking.common.Result;
import com.pocket.gocooking.system.entity.Step;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pocket
 */
public interface StepService{
    /**
     * 通过dishId查询步骤
     * @param dishId
     * @return List
     */
    List<Step> selectStepsByDishId(Integer dishId);

    /**
     * 批量新增step
     * @param step
     * @param dishId
     * @return
     */
    Integer insertStepsByDishId(String step, Integer dishId);

    /**
     * 删除相关步骤
     * @param dishId
     * @return
     */
    Integer deleteSteps(Integer dishId);
}
