package com.pocket.gocooking.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@TableName("dish_table")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dish_id", type = IdType.AUTO)
    private Integer dishId;

    private String name;

    private LocalTime time;

    private String difficulty;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Dish{" +
            "dishId = " + dishId +
            ", name = " + name +
            ", time = " + time +
            ", difficulty = " + difficulty +
        "}";
    }
}
