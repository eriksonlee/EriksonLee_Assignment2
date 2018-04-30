package com.Baristamatic.common;

public class OutOfStockException extends Exception{

    public OutOfStockException(String msg){super(msg);}

    public String getMessage(String msg) {
        return "Out of stock: " + msg;
    }
}
