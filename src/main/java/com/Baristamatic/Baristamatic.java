package com.Baristamatic;

import com.Baristamatic.Inventories.*;
import com.Baristamatic.common.OutOfStockException;

import java.util.Scanner;



public class Baristamatic {

    public static void main (String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Inventory thisInventory = InventoryImplFactory.createInventory();

        while(true) {
            // At program startup, and following the processing of every command,
            // the machine inventory and the drink menu should be displayed.
            thisInventory.displayMenu();

            try {

                String userInput = "";

                // Blank input lines should be ignored
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

                        // do not allow input of type "001"
                        boolean zerosAtStart = (userInput.charAt(0)!= '0');

                        // [1-6] - order the drink with the corresponding number in the menu
                        if ((zerosAtStart) && ((drinkNumber > 0) && (drinkNumber <= thisInventory.getDrinkNames().size()))) {

                            if (!thisInventory.isDrinkInStock(drinkNumber-1)) {
                                System.out.println("Out of stock: "+ thisInventory.getDrinkNames().get(drinkNumber-1));
                            }
                            else {
                                thisInventory.dispenseDrink(drinkNumber-1);
                            }

                        } else {
                            // Because drink-number does not exist.
                            System.out.println("Invalid selection: "+userInput);

                        } // inner if-else conditions

                    } catch (NumberFormatException e) {
                        // Because we didn't get a number.
                        System.out.println("Invalid selection: "+userInput);

                    } // inner try-catch

                } // outer if-else conditions

            } catch (Exception e) {
                // Problem reading from standard input or something else
                System.out.println(e.getMessage());

            } //  outer try-catch
        } // while-loop


        try {
            keyboard.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


