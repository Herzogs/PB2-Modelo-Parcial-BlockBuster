package com.parcial.blockbuster;

public class StockNoDisponibleException extends Exception{

    private String msg;

    public StockNoDisponibleException(String msg) {
        this.msg = msg;
    }

    public String getMessege() {
        return msg;
    }
}
