package com.pocket.gocooking.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pocket
 * @date 2023-8-9 16:08:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    /**
     * 主键
      */
    Integer id;
    /**
     * 菜品主键
     */
    Integer dishId;
    /**
     * 步骤编号
     */
    Integer step;
    /**
     * 步骤描述
     */
    String description;

}
