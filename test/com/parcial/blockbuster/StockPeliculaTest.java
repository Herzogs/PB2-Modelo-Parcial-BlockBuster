package com.parcial.blockbuster;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class StockPeliculaTest {

    private StockPelicula stock;

    @Before
    public void crearElContenedorStock(){
        this.stock = new StockPelicula();
    }

    @Test
    public void queSeIntenteAgregarUnaPeliculaAlStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        Assert.assertEquals(true,this.stock.agregarAlStock(peli));
    }

    @Test
    public void queSeIntenteAgregarUnaPeliculaRepetidaAlStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        Assert.assertEquals(false,this.stock.agregarAlStock(peli));
    }

    @Test
    public void queSeAgregueEjemplaresAUnaPeliculaQueEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        Assert.assertEquals(true,this.stock.agregarEjemplarAlStock(peli,3));
    }

    @Test
    public void queSeAgregueEjemplaresAUnaPeliculaQueNoEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        Pelicula peli2 = new Pelicula("0002","Star Wars","1988",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        Assert.assertEquals(false,this.stock.agregarEjemplarAlStock(peli2,3));
    }

    @Test
    public void queSeDecrementeEjemplaresAUnaPeliculaQueEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        this.stock.agregarEjemplarAlStock(peli,3);
        Assert.assertEquals(true,this.stock.decrementarEjemplarDelStock(peli));
    }

    @Test
    public void queSeDecrementeEjemplaresAUnaPeliculaQueNoEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        Pelicula peli2 = new Pelicula("0002","Star Wars","1988",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        this.stock.agregarEjemplarAlStock(peli,3);
        Assert.assertEquals(false,this.stock.decrementarEjemplarDelStock(peli2));
    }

    @Test
    public void queSeIntenteDecrementarEjemplaresAUnaPeliculaQueSiEsteEnStockPeroNoHaySuficienteStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        this.stock.agregarEjemplarAlStock(peli,3);
        this.stock.decrementarEjemplarDelStock(peli);
        this.stock.decrementarEjemplarDelStock(peli);
        this.stock.decrementarEjemplarDelStock(peli);
        Assert.assertEquals(false,this.stock.decrementarEjemplarDelStock(peli));
    }

    @Test
    public void queSeIntenteRemoverUnaPeliculaQueNoEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        Pelicula peli2 = new Pelicula("0002","Star Wars","1988",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        this.stock.agregarEjemplarAlStock(peli,3);
        Assert.assertEquals(false,this.stock.quitarDelStock(peli2));
    }

    @Test
    public void queSeIntenteRemoverUnaPeliculaQueSiEsteEnStock(){
        Pelicula peli = new Pelicula("0001","Star Wars","1980",tipoPelicula.ACCION);
        this.stock.agregarAlStock(peli);
        Assert.assertEquals(true,this.stock.quitarDelStock(peli));
    }
}
