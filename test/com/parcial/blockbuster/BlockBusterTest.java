package com.parcial.blockbuster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BlockBusterTest {

    private BlockBuster bb;

    @Before
    public void seCreaLaAplicacion() {
        this.bb = new BlockBuster();
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaYUnaConsolaQueEstanEnStock() throws StockNoDisponibleException {
        Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
        Consola n64 = new Consola("0001", tipoConsola.NINTENDO_64);
        n64.setListaJuegos((List.of(new Juego("Super Mario 64", "2000"))));
        this.bb.agregarPelicula(peli);
        this.bb.agregarConsola(n64);
        this.bb.agregarEjemplarALaConsola(n64, 3);
        this.bb.agregarEjemplarALaPelicula(peli, 5);
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Assert.assertEquals(true, this.bb.alquilar(peli, n64, cli));
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaYUnaConsolaQueNoEstanEnStock() {
        Exception myException = null;
        try {
            Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
            Consola n64 = new Consola("0001", tipoConsola.NINTENDO_64);
            n64.setListaJuegos((List.of(new Juego("Super Mario 64", "2000"))));
            this.bb.agregarPelicula(peli);
            this.bb.agregarConsola(n64);
            Cliente cli = new Cliente(1, "Cristian Feldman", 33);
            this.bb.alquilar(peli, n64, cli);
        } catch (StockNoDisponibleException e) {
            myException = e;
        } finally {
            Assert.assertEquals(StockNoDisponibleException.class, myException.getClass());
        }
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaQueNoEstanEnStock() {
        Exception myException = null;
        try {
            Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
            this.bb.agregarPelicula(peli);
            Cliente cli = new Cliente(1, "Cristian Feldman", 33);
            this.bb.alquilar(peli, null, cli);
        } catch (StockNoDisponibleException e) {
            myException = e;
        } finally {
            Assert.assertEquals(StockNoDisponibleException.class, myException.getClass());
        }
    }

    @Test
    public void queSeIntentaAlquilarUnaConsolaQueNoEstanEnStock() {
        Exception myException = null;
        try {
            Consola n64 = new Consola("0001", tipoConsola.NINTENDO_64);
            n64.setListaJuegos((List.of(new Juego("Super Mario 64", "2000"))));
            this.bb.agregarConsola(n64);
            Cliente cli = new Cliente(1, "Cristian Feldman", 33);
            this.bb.alquilar(null, n64, cli);
        } catch (StockNoDisponibleException e) {
            myException = e;
        } finally {
            Assert.assertEquals(StockNoDisponibleException.class, myException.getClass());
        }
    }

    @Test
    public void queNoRealizaElAlquilerYaPeliculaEConsolaSonNULL() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Assert.assertEquals(false, this.bb.alquilar(null, null, cli));
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaYUnaConsolaPeroYaLLegaronAlLimite() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
        Pelicula peli2 = new Pelicula("0002", "Star Wars", "1980", tipoPelicula.ACCION);
        Pelicula peli3 = new Pelicula("0003", "Star Wars", "1980", tipoPelicula.ACCION);
        Pelicula peli4 = new Pelicula("0004", "Star Wars", "1980", tipoPelicula.ACCION);
        this.bb.agregarPelicula(peli);
        this.bb.agregarPelicula(peli2);
        this.bb.agregarPelicula(peli3);
        this.bb.agregarPelicula(peli4);
        this.bb.agregarEjemplarALaPelicula(peli, 3);
        this.bb.agregarEjemplarALaPelicula(peli2, 3);
        this.bb.agregarEjemplarALaPelicula(peli3, 3);
        this.bb.agregarEjemplarALaPelicula(peli4, 3);
        this.bb.alquilar(peli, null, cli);
        this.bb.alquilar(peli2, null, cli);
        Assert.assertEquals(false, this.bb.alquilar(peli3, null, cli));
    }

    @Test
    public void queSeIntentaAlquilarUnaConsolaPeroYaHabiaLLegadoAlLimite() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Consola con1 = new Consola("0001", tipoConsola.NINTENDO_64);
        Consola con2 = new Consola("0002", tipoConsola.SEGA_GENESIS);
        this.bb.agregarConsola(con1);
        this.bb.agregarConsola(con2);
        this.bb.agregarEjemplarALaConsola(con1, 3);
        this.bb.agregarEjemplarALaConsola(con2, 3);
        this.bb.alquilar(null, con1, cli);
        Assert.assertEquals(false, this.bb.alquilar(null, con2, cli));
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaCondicionadaPeroElClienteEsMenorDeEdad() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Pedro Gomez", 15);
        Pelicula peli = new Pelicula("0001", "XXXXX", "2010", tipoPelicula.CONDICIONADA);
        this.bb.agregarPelicula(peli);
        this.bb.agregarEjemplarALaPelicula(peli, 3);
        Assert.assertEquals(false, this.bb.alquilar(peli, null, cli));
    }

    @Test
    public void queSeIntentaAlquilarUnaPeliculaCondicionadaPeroElClienteEsMayorDeEdad() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Pedro Gomez", 19);
        Pelicula peli = new Pelicula("0001", "XXXXX", "2010", tipoPelicula.CONDICIONADA);
        this.bb.agregarPelicula(peli);
        this.bb.agregarEjemplarALaPelicula(peli, 3);
        Assert.assertEquals(true, this.bb.alquilar(peli, null, cli));
    }

    @Test
    public void queSeIntentaDevolverUnaPeliculaYUnaConsolaQueYaEstabanAlquiladas() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
        Consola n64 = new Consola("0001", tipoConsola.NINTENDO_64);
        n64.setListaJuegos((List.of(new Juego("Super Mario 64", "2000"))));
        this.bb.agregarPelicula(peli);
        this.bb.agregarConsola(n64);
        this.bb.agregarEjemplarALaConsola(n64, 3);
        this.bb.agregarEjemplarALaPelicula(peli, 5);
        this.bb.alquilar(peli, n64, cli);
        Assert.assertEquals(true, this.bb.devolucion(peli, n64, cli));
    }

    @Test
    public void queSeIntentaDevolverUnaPeliculaYUnaConsolaQueNoHabianSidoAlquiladas() throws StockNoDisponibleException {
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
        Consola n64 = new Consola("0001", tipoConsola.NINTENDO_64);
        Consola con2 = new Consola("0002", tipoConsola.SEGA_GENESIS);
        n64.setListaJuegos((List.of(new Juego("Super Mario 64", "2000"))));
        this.bb.agregarPelicula(peli);
        this.bb.agregarConsola(n64);
        this.bb.agregarEjemplarALaConsola(n64, 3);
        this.bb.agregarEjemplarALaPelicula(peli, 5);
        this.bb.alquilar(peli, n64, cli);
        Assert.assertEquals(false, this.bb.devolucion(new Pelicula("0002", "YYYYY", "2020", tipoPelicula.ACCION), con2, cli));
    }

    @Test
    public void queSeIntentaVenderUnaPelicula() throws StockNoDisponibleException {
        Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
        this.bb.agregarPelicula(peli);
        this.bb.agregarEjemplarALaPelicula(peli, 5);
        Cliente cli = new Cliente(1, "Cristian Feldman", 33);
        Assert.assertEquals(true, this.bb.venderPelicula(peli, cli));
    }

    @Test
    public void queSeIntentaVenderUnaPeliculaQueNoTieneStock() throws StockNoDisponibleException {
        Exception myException = null;
        try {
            Pelicula peli = new Pelicula("0001", "Star Wars", "1980", tipoPelicula.ACCION);
            this.bb.agregarPelicula(peli);
            Cliente cli = new Cliente(1, "Cristian Feldman", 33);
            this.bb.venderPelicula(peli, cli);
        } catch (StockNoDisponibleException e) {
            myException = e;
        } finally {
            Assert.assertEquals(StockNoDisponibleException.class, myException.getClass());
        }
    }

}
