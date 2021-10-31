package com.parcial.blockbuster;

import java.util.HashMap;
import java.util.Map;

public class StockConsola implements Stock<Consola> {
    private Map<Consola, Integer> stock;

    public StockConsola() {
        this.stock = new HashMap<>();
    }

    public Boolean agregarAlStock(Consola con){
        Boolean est = false;
        if(!this.stock.containsKey(con)){
            this.stock.put(con,0);
            est = true;
        }
        return est;
    }

    public Boolean agregarEjemplarAlStock(Consola con, Integer cant){
        Boolean est = false;
        if(this.stock.containsKey(con)){
            this.stock.replace(con,this.stock.get(con)+cant);
            est = true;
        }
        return est;
    }

    public Boolean decrementarEjemplarDelStock(Consola con) throws StockNoDisponibleException {
        Boolean est = false;
        if( this.stock.containsKey(con)) {
            if (this.stock.get(con) == 0)
                throw new StockNoDisponibleException("No hay mas stock disponible");
            else {
                this.stock.replace(con, this.stock.get(con) - 1);
                est = true;
            }
        }
        return est;
    }

    @Override
    public Boolean quitarDelStock(Consola con) {
        Boolean est = false;
        if(this.stock.containsKey(con)){
            this.stock.remove(con);
            est = true;
        }
        return est;
    }

    public Map<Consola, Integer> getstock() {
        return stock;
    }
}
