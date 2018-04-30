package com.Baristamatic.Drinks;


import java.util.HashMap;

public interface Drink {

    String getDrinkName();

    double getDrinkPrice();

    void setDrinkPrice(double inputDrinkPrice);

    void updateStock(boolean input);
    boolean isDrinkInStock();

    HashMap< String, Integer> getDrinkRecipe();

}
