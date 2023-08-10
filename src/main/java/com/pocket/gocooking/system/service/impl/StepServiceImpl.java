package com.pocket.gocooking.system.service.impl;

import com.pocket.gocooking.system.entity.Step;
import com.pocket.gocooking.system.mapper.StepMapper;
import com.pocket.gocooking.system.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pocket
 */
@Service
public class StepServiceImpl implements StepService {
    @Autowired
    private StepMapper stepMapper;
    @Override
    public List<Step> selectStepsByDishId(Integer dishId) {
        return stepMapper.selectStepsByDishId(dishId);
    }

    @Override
    public Integer insertStepsByDishId(String step, Integer dishId) {
        // 分割
        String[] steps = step.split("\n");
        int stepCount = 1;
        var list = new ArrayList<Step>();
        for (String s : steps) {
            Step tempStep = new Step(null, dishId, stepCount++, s);
            list.add(tempStep);
        }
        return stepMapper.insertStepsByDishId(list);
    }

    @Override
    public Integer deleteSteps(Integer dishId) {
        return stepMapper.deleteStepsByDishId(dishId);
    }
}
