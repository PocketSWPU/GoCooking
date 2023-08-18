package com.pocket.gocooking.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {

    private Integer dishId;

    private String name;

    private LocalTime time;

    private String difficulty;

    private Integer creatId;


}
