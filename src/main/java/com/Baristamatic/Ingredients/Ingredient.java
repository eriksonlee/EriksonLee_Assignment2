package com.Baristamatic.Ingredients;



public interface Ingredient {

    String getIngredientName();
    void resetIngredientQty();
    void updateIngredientQty(int inputIngredientQty) ;
    int getIngredientQty();
    double getIngredientPrice();

}
