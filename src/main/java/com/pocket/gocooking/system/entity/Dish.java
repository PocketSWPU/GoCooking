package com.pocket.gocooking.system.entity;

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
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

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
