package com.Baristamatic.Inventories;


import com.Baristamatic.Drinks.Drink;
import com.Baristamatic.common.OutOfStockException;

import java.util.ArrayList;

public interface Inventory {

    void setIngredientInventory();
    void getIngredientInventory();
    void updateIngredientInventory(int inputIngredientId, int updateAmt);
    void resetIngredientInventory();

    void displayMenu();

    ArrayList<String> getDrinkNames();
    void setDrinkInventory();
    void getDrinkInventory();
    void dispenseDrink(int inputOption) throws OutOfStockException;
    boolean isDrinkInStock(int inputOption);

}
