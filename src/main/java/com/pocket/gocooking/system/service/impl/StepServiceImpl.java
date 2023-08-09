package com.pocket.gocooking.system.service.impl;

import com.pocket.gocooking.system.entity.Step;
import com.pocket.gocooking.system.mapper.StepMapper;
import com.pocket.gocooking.system.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
