package com.parcial.blockbuster;

import java.util.Objects;

public class Pelicula implements Comparable<Pelicula>,Alquilable {

    private String id;
    private String titulo;
    private String anio;
    private tipoPelicula tipo;

    public Pelicula(String id, String titulo, String anio, tipoPelicula tipo) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pelicula)) return false;
        Pelicula pelicula = (Pelicula) o;
        return id.equals(pelicula.id) && titulo.equals(pelicula.titulo) && anio.equals(pelicula.anio) && tipo == pelicula.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, anio, tipo);
    }

    @Override
    public Boolean esAlquilable() {
        return this.tipo.equals(tipoPelicula.ACCION) || this.tipo.equals(tipoPelicula.SUSPENSO) || this.tipo.equals(tipoPelicula.COMEDIA) || this.tipo.equals(tipoPelicula.CONDICIONADA);
    }

    @Override
    public int compareTo(Pelicula o) {
        return Integer.parseInt(this.anio)-Integer.parseInt(o.getAnio());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public tipoPelicula getTipo() {
        return tipo;
    }

    public void setTipo(tipoPelicula tipo) {
        this.tipo = tipo;
    }
}


