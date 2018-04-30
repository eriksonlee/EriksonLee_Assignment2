package com.Baristamatic.Ingredients;

public class IngredientImplFactory {

    public static Ingredient createIngredient( String inputIngredientName, double inputIngredientPrice){
        return new IngredientImpl(inputIngredientName, inputIngredientPrice);
    }

}
