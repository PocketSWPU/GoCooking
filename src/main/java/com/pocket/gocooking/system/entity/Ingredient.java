package com.pocket.gocooking.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {

    private Integer ingredientId;

    private Integer categoryId;

    private String ingredientName;
}
