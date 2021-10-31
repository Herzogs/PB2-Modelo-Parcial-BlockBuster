package com.parcial.blockbuster;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Consola implements Alquilable{
    private String id;
    private tipoConsola tipo;
    private List<Juego> listaJuegos;

    public Consola(String id, tipoConsola tipo) {
        this.id = id;
        this.tipo = tipo;
        this.listaJuegos = new LinkedList<>();
    }

    public void agregarJuegos(List<Juego> lista){
        this.listaJuegos.addAll(lista);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consola)) return false;
        Consola consola = (Consola) o;
        return id.equals(consola.id) && tipo == consola.tipo && listaJuegos.equals(consola.listaJuegos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, listaJuegos);
    }

    @Override
    public Boolean esAlquilable() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public tipoConsola getTipo() {
        return tipo;
    }

    public void setTipo(tipoConsola tipo) {
        this.tipo = tipo;
    }

    public List<Juego> getListaJuegos() {
        return listaJuegos;
    }

    public void setListaJuegos(List<Juego> listaJuegos) {
        this.listaJuegos = listaJuegos;
    }
}
