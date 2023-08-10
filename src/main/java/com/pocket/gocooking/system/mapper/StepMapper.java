package com.pocket.gocooking.system.mapper;

import com.pocket.gocooking.system.entity.Step;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author Pocket
 */
@Mapper
public interface StepMapper {
    /**
     * 通过dishId查询相关步骤
     * @param dishId
     * @return
     */
    List<Step> selectStepsByDishId(Integer dishId);

    /**
     * 添加一个Step
     * @param steps
     * @return
     */
    Integer insertStepsByDishId(List<Step> steps);

    /**
     * 删除
     * @param dishId
     * @return
     */
    Integer deleteStepsByDishId(Integer dishId);
}
