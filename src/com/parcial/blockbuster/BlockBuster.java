package com.parcial.blockbuster;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BlockBuster {

    /*private List<Pelicula> listaDePelicula;
    private List<Consola> listaDeConsola;*/
    private List<Alquiler> listaDeAlquiler;
    private StockPelicula stockPelicula;
    private StockConsola stockConsola;

    public BlockBuster() {
        /*this.listaDePelicula = new LinkedList<>();
        this.listaDeConsola = new LinkedList<>();*/
        this.listaDeAlquiler = new LinkedList<>();
        this.stockConsola = new StockConsola();
        this.stockPelicula = new StockPelicula();
    }

    public Boolean agregarPelicula(Pelicula peli) {
        return this.stockPelicula.agregarAlStock(peli);
    }

    public Boolean agregarEjemplarALaPelicula(Pelicula peli, Integer can) {
        return this.stockPelicula.agregarEjemplarAlStock(peli, can);
    }

    public Boolean agregarConsola(Consola cons) {
        return this.stockConsola.agregarAlStock(cons);
    }

    public Boolean agregarEjemplarALaConsola(Consola cons, Integer can) {
        return this.stockConsola.agregarEjemplarAlStock(cons, can);
    }

    private Integer obtenerCantidadDePeliculasAlquilasPorNroSocio(Integer nroSocio) {
        Integer cant = 0;
        for (Alquiler alq : this.listaDeAlquiler) {
            if (nroSocio.equals(alq.getNroSocio()) && !alq.getIdPelicula().equals("00000")) {
                cant++;
            }
        }
        return cant;
    }

    private Integer obtenerCantidadDeConsolasAlquilasPorNroSocio(Integer nroSocio) {
        Integer cant = 0;
        for (Alquiler alq : this.listaDeAlquiler) {
            if (nroSocio.equals(alq.getNroSocio()) && !alq.getIdConsola().equals("00000")) {
                cant++;
            }
        }
        return cant;
    }

    public Boolean alquilar(Pelicula peli, Consola con, Cliente cli) throws StockNoDisponibleException {
        Boolean est = false, estPeli = false, estCons = false;
        Integer cant = this.obtenerCantidadDePeliculasAlquilasPorNroSocio(cli.getNroSocio());
        Integer cantCon = this.obtenerCantidadDeConsolasAlquilasPorNroSocio(cli.getNroSocio());
        if (cant < 3 && cantCon == 0) {
            Alquiler nuevo = new Alquiler("0000", "0000", 0);
            if (peli != null && peli.esAlquilable()) {
                if (peli.getTipo().equals(tipoPelicula.CONDICIONADA)) {
                    if (cli.getEdad() > 18) {
                        nuevo.setIdPelicula(peli.getId());
                        this.stockPelicula.decrementarEjemplarDelStock(peli);
                        estPeli = true;
                    }
                } else if (this.stockPelicula.decrementarEjemplarDelStock(peli)) {
                    nuevo.setIdPelicula(peli.getId());
                    estPeli = true;
                }
            }
            if (con != null && this.stockConsola.decrementarEjemplarDelStock(con)) {
                nuevo.setIdConsola(con.getId());
                estCons = true;
            }
            if (estPeli || estCons) {
                nuevo.setNroSocio(cli.getNroSocio());
                this.listaDeAlquiler.add(nuevo);
                est = true;
            }
        }
        return est;
    }

    private Integer obtenerIndiceDeDevolucionPelicula(String cod, Integer socio) {
        Integer i = 0;
        Boolean est = false;
        for (; i < this.listaDeAlquiler.size() && !est; i++) {
            if (cod.equals(this.listaDeAlquiler.get(i).getIdPelicula()) && socio.equals(this.listaDeAlquiler.get(i).getNroSocio())) {
                est = true;
            }
        }
        return est ? i - 1 : -1;
    }

    private Integer obtenerIndiceDeDevolucionConsola(String cod, Integer socio) {
        Integer i = 0;
        Boolean est = false;
        for (; i < this.listaDeAlquiler.size() && !est; i++) {
            if (cod.equals(this.listaDeAlquiler.get(i).getIdConsola()) && socio.equals(this.listaDeAlquiler.get(i).getNroSocio())) {
                est = true;
            }
        }
        return est ? i - 1 : -1;
    }

    public Boolean devolucion(Pelicula peli, Consola cons, Cliente cli) {
        Boolean devPeli = false, devCon = false;
        Integer indPelicula = -1, indConsola = -1;
        if (peli != null)
            indPelicula = this.obtenerIndiceDeDevolucionPelicula(peli.getId(), cli.getNroSocio());

        if (cons != null)
            indConsola = this.obtenerIndiceDeDevolucionConsola(cons.getId(), cli.getNroSocio());

        if (!indPelicula.equals(-1) || !indConsola.equals(-1)) {
            if (!indPelicula.equals(-1)) {
                this.listaDeAlquiler.get(indPelicula).setIdPelicula("0000");
                this.stockPelicula.agregarEjemplarAlStock(peli, 1);
                if (this.listaDeAlquiler.get(indPelicula).getIdConsola().equals("0000")) {
                    this.listaDeAlquiler.remove(indPelicula);
                }
                devPeli = true;
            }
            if (!indConsola.equals(-1)) {
                this.listaDeAlquiler.get(indConsola).setIdConsola("0000");
                this.stockConsola.agregarEjemplarAlStock(cons, 1);
                if (this.listaDeAlquiler.get(indConsola).getIdPelicula().equals("0000")) {
                    this.listaDeAlquiler.remove(indConsola);
                }
                devCon = true;
            }
            if (devCon == devPeli) {
                this.listaDeAlquiler.remove(devCon);
            }
        }
        return devPeli || devCon;
    }

    public Boolean venderPelicula(Pelicula peli, Cliente cli) throws StockNoDisponibleException {
        Boolean est = false;
        if (peli != null && !peli.getTipo().equals(tipoPelicula.CONDICIONADA)) {
            this.stockPelicula.decrementarEjemplarDelStock(peli);
            est = true;
        }
        return est;
    }

}
