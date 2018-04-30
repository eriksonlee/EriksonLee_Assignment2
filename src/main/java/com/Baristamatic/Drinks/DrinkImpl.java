package com.Baristamatic.Drinks;

import java.util.Comparator;
import java.util.HashMap;

public class DrinkImpl implements Drink {

    private final String drinkName;
    private final HashMap< String, Integer > drinkRecipe;
    private double drinkPrice;
    private boolean drinkInStock;

    public DrinkImpl(String inputDrinkName, HashMap< String, Integer> inputDrinkRecipe){

        this.drinkName = inputDrinkName;
        this.drinkRecipe = inputDrinkRecipe;

    }

    public void updateStock(boolean input){
        this.drinkInStock = input;
    }

    public boolean isDrinkInStock(){
        return this.drinkInStock;
    }

    public void setDrinkPrice(double inputDrinkPrice){
        this.drinkPrice = inputDrinkPrice;
    }

    public double getDrinkPrice(){
        return this.drinkPrice;
    }

    public String getDrinkName(){

        return this.drinkName;

    }

    public HashMap< String, Integer> getDrinkRecipe(){

        return this.drinkRecipe;

    }


}
