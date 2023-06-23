package com.pocket.gocooking.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author PengZF
 * @since 2023-06-24
 */
@TableName("ingredient_table")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ingredient_id", type = IdType.AUTO)
    private Integer ingredientId;

    private Integer categoryId;

    private String ingredientName;

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
            "ingredientId = " + ingredientId +
            ", categoryId = " + categoryId +
            ", ingredientName = " + ingredientName +
        "}";
    }
}
