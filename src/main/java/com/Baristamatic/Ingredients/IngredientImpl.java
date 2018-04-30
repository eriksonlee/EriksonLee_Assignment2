package com.Baristamatic.Ingredients;


public class IngredientImpl implements Ingredient {

    private final String ingredientName;
    private int ingredientQty;
    private final double ingredientPrice;

    public IngredientImpl(String inputIngredientName, double inputIngredientPrice){

        this.ingredientName = inputIngredientName;
        this.ingredientPrice = inputIngredientPrice;
        this.resetIngredientQty();

    }

    public String getIngredientName(){

        return this.ingredientName;

    }

    public void resetIngredientQty(){

        this.ingredientQty = 10;

    }

    public void updateIngredientQty(int inputIngredientQty) {

        if ((this.ingredientQty - inputIngredientQty) < 0){
            this.ingredientQty = 0;
        }
        else{
            this.ingredientQty -= inputIngredientQty;
        }

    }

    public int getIngredientQty(){

        return this.ingredientQty;

    }

    public double getIngredientPrice(){

        return this.ingredientPrice;

    }

}
