package com.Baristamatic.Inventories;

public class InventoryImplFactory {

    public static Inventory createInventory(){
        return new InventoryImpl();
    }

}
