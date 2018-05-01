package com.Baristamatic;

import com.Baristamatic.Inventories.*;
import com.Baristamatic.common.OutOfStockException;

import java.util.Scanner;



public class Baristamatic {

    public static void main (String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Inventory thisInventory = InventoryImplFactory.createInventory();

        while(true) {
            // Display menu at startup
            thisInventory.displayMenu();

            try {

                String userInput = "";

                // Ignore blank inputs
                while(userInput.length() <= 0)
                    userInput = keyboard.nextLine().trim();

                if (userInput.toLowerCase().equals("q")) {
                    // 'Q' or 'q' - quit the application
                    break;

                } else if (userInput.toLowerCase().equals("r")) {
                    // 'R' or 'r' - reStock the inventory and redisplay the menu
                    thisInventory.resetIngredientInventory();

                } else {

                    try {
                        int drinkNumber = Integer.valueOf(userInput);

                        // Dont allow leading 0
                        boolean zerosAtStart = (userInput.charAt(0)!= '0');

                        // Enter drink number
                        if ((zerosAtStart) && ((drinkNumber > 0) && (drinkNumber <= thisInventory.getDrinkNames().size()))) {

                            if (!thisInventory.isDrinkInStock(drinkNumber-1)) {
                                System.out.println("Out of stock: "+ thisInventory.getDrinkNames().get(drinkNumber-1));
                            }
                            else {
                                thisInventory.dispenseDrink(drinkNumber-1);
                            }

                        } else {
                            // Invalid drink number
                            System.out.println("Invalid selection: "+userInput);

                        }

                    } catch (NumberFormatException e) {
                        // Number was not entered
                        System.out.println("Invalid selection: "+userInput);

                    }
                }

            } catch (Exception e) {
                // Problem reading from standard input or something else
                System.out.println(e.getMessage());

            }
        }


        try {
            keyboard.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


