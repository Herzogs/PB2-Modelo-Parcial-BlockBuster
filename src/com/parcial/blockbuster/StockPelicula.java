package com.parcial.blockbuster;

import java.util.HashMap;
import java.util.Map;

public class StockPelicula implements Stock<Pelicula> {

    private Map<Pelicula,Integer> stockPelicula;

    public StockPelicula(){
        this.stockPelicula = new HashMap<>();
    }

    public Boolean agregarAlStock(Pelicula peli){
        Boolean est = false;
        if(!this.stockPelicula.containsKey(peli)){
            this.stockPelicula.put(peli,0);
            est = true;
        }
        return est;
    }

    public Boolean agregarEjemplarAlStock(Pelicula peli, Integer cant){
        Boolean est = false;
        if(this.stockPelicula.containsKey(peli)){
            this.stockPelicula.replace(peli,this.stockPelicula.get(peli)+cant);
            est = true;
        }
        return est;
    }

    public Boolean decrementarEjemplarDelStock(Pelicula peli){
        Boolean est = false;
        if( this.stockPelicula.containsKey(peli) && this.stockPelicula.get(peli) > 0){
            this.stockPelicula.replace(peli, this.stockPelicula.get(peli)-1);
            est = true;
        }
        return est;
    }

    @Override
    public Boolean quitarDelStock(Pelicula con) {
        Boolean est = false;
        if(this.stockPelicula.containsKey(con)){
            this.stockPelicula.remove(con);
            est = true;
        }
        return est;
    }

    public Map<Pelicula, Integer> getStockPelicula() {
        return stockPelicula;
    }
}
