package com.parcial.blockbuster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockConsolaTest {

    private StockConsola stock;

    @Before
    public void crearElContenedorStock(){
        this.stock = new StockConsola();
    }

    @Test
    public void queSeIntenteAgregarUnaConsolaAlStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        Assert.assertEquals(true,this.stock.agregarAlStock(cons));
    }

    @Test
    public void queSeIntenteAgregarUnaConsolaRepetidaAlStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        this.stock.agregarAlStock(cons);
        Assert.assertEquals(false,this.stock.agregarAlStock(cons));
    }

    @Test
    public void queSeAgregueEjemplaresAUnaConsolaQueEsteEnStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        this.stock.agregarAlStock(cons);
        Assert.assertEquals(true,this.stock.agregarEjemplarAlStock(cons,3));
    }

    @Test
    public void queSeAgregueEjemplaresAUnaConsolaQueNoEsteEnStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        Consola cons2 = new Consola("0002",tipoConsola.PLAYSTATION_3);
        this.stock.agregarAlStock(cons);
        Assert.assertEquals(false,this.stock.agregarEjemplarAlStock(cons2,3));
    }

    @Test
    public void queSeDecrementeEjemplaresAUnaConsolaQueEsteEnStock() throws StockNoDisponibleException{
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        this.stock.agregarAlStock(cons);
        this.stock.agregarEjemplarAlStock(cons,3);
        Assert.assertEquals(true,this.stock.decrementarEjemplarDelStock(cons));
    }

    @Test
    public void queSeDecrementeEjemplaresAUnaConsolaQueNoEsteEnStock() throws StockNoDisponibleException{
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        Consola cons2 = new Consola("0002",tipoConsola.PLAYSTATION_3);
        this.stock.agregarAlStock(cons);
        this.stock.agregarEjemplarAlStock(cons,3);
        Assert.assertEquals(false,this.stock.decrementarEjemplarDelStock(cons2));
    }

    @Test
    public void queSeIntenteDecrementarEjemplaresAUnaConsolaQueSiEsteEnStockPeroNoHaySuficienteStock(){
        Exception myException = null;
        try {
            Consola cons = new Consola("0001", tipoConsola.NINTENDO_64);
            this.stock.agregarAlStock(cons);
            this.stock.agregarEjemplarAlStock(cons, 3);
            this.stock.decrementarEjemplarDelStock(cons);
            this.stock.decrementarEjemplarDelStock(cons);
            this.stock.decrementarEjemplarDelStock(cons);
            this.stock.decrementarEjemplarDelStock(cons);
        }catch (StockNoDisponibleException e){
            myException = e;
        }finally {
            Assert.assertEquals(StockNoDisponibleException.class,myException.getClass());
        }

    }

    @Test
    public void queSeIntenteRemoverUnaConsolaQueNoEsteEnStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        Consola cons2 = new Consola("0002",tipoConsola.PLAYSTATION_3);
        this.stock.agregarAlStock(cons);
        this.stock.agregarEjemplarAlStock(cons,3);
        Assert.assertEquals(false,this.stock.quitarDelStock(cons2));
    }

    @Test
    public void queSeIntenteRemoverUnaConsolaQueSiEsteEnStock(){
        Consola cons = new Consola("0001",tipoConsola.NINTENDO_64);
        this.stock.agregarAlStock(cons);
        Assert.assertEquals(true,this.stock.quitarDelStock(cons));
    }
}
