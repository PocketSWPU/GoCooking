package com.pocket.gocooking.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@Data
public class Ingredient implements Serializable {

    private Integer ingredientId;

    private Integer categoryId;

    private String ingredientName;
}
