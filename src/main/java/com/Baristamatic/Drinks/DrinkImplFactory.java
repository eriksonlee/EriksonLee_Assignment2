package com.Baristamatic.Drinks;

import java.util.HashMap;

public class DrinkImplFactory {

    public static Drink createDrink(String inputDrinkName, HashMap< String, Integer> inputDrinkRecipe){
        return new DrinkImpl(inputDrinkName, inputDrinkRecipe);
    }

}
