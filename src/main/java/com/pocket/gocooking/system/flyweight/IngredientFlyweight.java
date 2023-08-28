package com.pocket.gocooking.system.flyweight;

import com.pocket.gocooking.system.entity.DishIngredient;
import com.pocket.gocooking.system.entity.Ingredient;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Pocket
 */
@Component
public class IngredientFlyweight {
    HashMap<String, DishIngredient> ingredientPool = new HashMap<>();

    public DishIngredient getIngredient(String ingredientName){
        if (!ingredientPool.containsKey(ingredientName)){
            DishIngredient ingredient = new DishIngredient();

            ingredientPool.put(ingredientName, ingredient);
        }
        return ingredientPool.get(ingredientName);
    }
}
