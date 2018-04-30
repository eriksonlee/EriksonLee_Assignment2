package com.Baristamatic.Inventories;

import com.Baristamatic.Drinks.Drink;
import com.Baristamatic.Drinks.DrinkImplFactory;
import com.Baristamatic.Ingredients.*;
import com.Baristamatic.common.OutOfStockException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InventoryImpl implements Inventory {

    private HashMap< String, HashMap< String, Integer >> drinkRecipes;
    private HashMap< String, Double> ingredientPrices;

    private ArrayList<String> ingredientNames;
    private ArrayList<Ingredient> ingredientInventory;

    private ArrayList<String> drinkNames;
    private ArrayList<Drink> drinkInventory;

    public InventoryImpl(){

        //Coffee
        this.drinkRecipes = new HashMap< String, HashMap<String, Integer>>();
        this.drinkRecipes.put("Coffee", new HashMap<String, Integer>());
        this.drinkRecipes.get("Coffee").put("Coffee", 3);
        this.drinkRecipes.get("Coffee").put("Sugar", 1);
        this.drinkRecipes.get("Coffee").put("Cream", 1);

        //Decaf Coffee
        this.drinkRecipes.put("Decaf Coffee", new HashMap<String, Integer>());
        this.drinkRecipes.get("Decaf Coffee").put("Decaf Coffee", 3);
        this.drinkRecipes.get("Decaf Coffee").put("Sugar", 1);
        this.drinkRecipes.get("Decaf Coffee").put("Cream", 1);

        //Caffe Latte
        this.drinkRecipes.put("Caffe Latte", new HashMap<String, Integer>());
        this.drinkRecipes.get("Caffe Latte").put("Espresso", 2);
        this.drinkRecipes.get("Caffe Latte").put("Steamed Milk", 1);

        //Caffe Americano
        this.drinkRecipes.put("Caffe Americano", new HashMap<String, Integer>());
        this.drinkRecipes.get("Caffe Americano").put("Espresso", 3);

        //Caffe Mocha
        this.drinkRecipes.put("Caffe Mocha", new HashMap<String, Integer>());
        this.drinkRecipes.get("Caffe Mocha").put("Espresso", 1);
        this.drinkRecipes.get("Caffe Mocha").put("Cocoa", 1);
        this.drinkRecipes.get("Caffe Mocha").put("Steamed Milk", 1);
        this.drinkRecipes.get("Caffe Mocha").put("Whipped Cream", 1);

        //Cappuccunio
        this.drinkRecipes.put("Cappuccino", new HashMap<String, Integer>());
        this.drinkRecipes.get("Cappuccino").put("Espresso", 2);
        this.drinkRecipes.get("Cappuccino").put("Steamed Milk", 1);
        this.drinkRecipes.get("Cappuccino").put("Foamed Milk", 1);

        //Ingredients
        this.ingredientPrices = new HashMap<String, Double>();
        this.ingredientPrices.put("Coffee", 0.75);
        this.ingredientPrices.put("Decaf Coffee", 0.75);
        this.ingredientPrices.put("Sugar", 0.25);
        this.ingredientPrices.put("Cream", 0.25);
        this.ingredientPrices.put("Steamed Milk", 0.35);
        this.ingredientPrices.put("Foamed Milk", 0.35);
        this.ingredientPrices.put("Espresso", 1.10);
        this.ingredientPrices.put("Cocoa", 0.90);
        this.ingredientPrices.put("Whipped Cream", 1.00);


        //Create a new ingredient inventory
        this.ingredientInventory = new ArrayList<Ingredient>();

        //Create a new drink inventory
        this.drinkInventory = new ArrayList<Drink>();

        //Create a name list for ingredients
        this.ingredientNames = new ArrayList<String>();
        this.ingredientNames.addAll(ingredientPrices.keySet());
        Collections.sort(this.ingredientNames);

        //Create a name list for drinks
        this.drinkNames = new ArrayList<String>();
        this.drinkNames.addAll(drinkRecipes.keySet());
        Collections.sort(this.drinkNames);

        //Set the ingredient inventory
        setIngredientInventory();

        //Set the drink inventory
        setDrinkInventory();

    }


    public void setIngredientInventory(){

        for (String ingName : this.ingredientNames){
            this.ingredientInventory.add(IngredientImplFactory.createIngredient(ingName, this.ingredientPrices.get(ingName)));
        }

    }
    public void getIngredientInventory(){

        System.out.println("Inventory:");
        for (Ingredient i : this.ingredientInventory){
            System.out.println(i.getIngredientName() + " " + i.getIngredientQty());
        }

    }

    public void updateIngredientInventory(int inputIngredientId, int updateAmt){

        this.ingredientInventory.get(inputIngredientId).updateIngredientQty(updateAmt);

    }

    public void resetIngredientInventory(){
        for(Ingredient i: this.ingredientInventory){
            i.resetIngredientQty();
        }
    }

    public void displayMenu(){

        getIngredientInventory();
        getDrinkInventory();

    }

    public void setDrinkInventory(){

        for (String drinkName : this.drinkNames){
            this.drinkInventory.add(DrinkImplFactory.createDrink(drinkName, this.drinkRecipes.get(drinkName)));
        }


        for (Drink d : this.drinkInventory){
            double drinkPrice = 0.0;
                for (Map.Entry<String,Integer> recipe : d.getDrinkRecipe().entrySet()){
                    double ingPrice = 0.0;
                    ingPrice = this.ingredientInventory.get(ingredientNameToId(recipe.getKey())).getIngredientPrice() * recipe.getValue();
                    drinkPrice = drinkPrice + ingPrice;

                }
            d.updateStock(true);
            d.setDrinkPrice(drinkPrice);
        }

    }


    public void getDrinkInventory(){
        System.out.println("Menu:");
        for(Drink d : this.drinkInventory){


            System.out.printf("%d,%s,$%.2f,%b\n",(drinkInventory.indexOf(d) + 1),d.getDrinkName(),d.getDrinkPrice(),isDrinkInStock(drinkInventory.indexOf(d)));

        }
    }

    public ArrayList<String> getDrinkNames(){
        return this.drinkNames;
    }

    public int ingredientNameToId(String inputName){
        return this.ingredientNames.indexOf(inputName);
    }

    public void dispenseDrink(int inputOption) throws OutOfStockException {

        if(!this.drinkInventory.get(inputOption).isDrinkInStock()){
            throw new OutOfStockException("Out of stock: " + this.drinkInventory.get(inputOption).getDrinkName());
        }
        else{

            for (Map.Entry<String, Integer> inputRecipe: this.drinkInventory.get(inputOption).getDrinkRecipe().entrySet()){
                updateIngredientInventory(ingredientNameToId(inputRecipe.getKey()),inputRecipe.getValue());
            }
        }
        System.out.println("Dispensing " + this.drinkInventory.get(inputOption).getDrinkName());

    }

    public boolean isDrinkInStock(int drinkId){

        boolean result = false;

        for( Map.Entry<String, Integer> drinkRecipe : this.drinkInventory.get(drinkId).getDrinkRecipe().entrySet()){
            if (this.ingredientInventory.get(ingredientNameToId(drinkRecipe.getKey())).getIngredientQty() >= drinkRecipe.getValue()){
                result = true;
            }
            else{
                return false;
            }
        }

        return result;

    }


}
