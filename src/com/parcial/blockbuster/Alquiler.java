package com.parcial.blockbuster;

import java.util.Objects;

public class Alquiler {
    private String idPelicula;
    private String idConsola;
    private Integer nroSocio;

    public Alquiler(String idPelicula, String idConsola, Integer nroSocio) {
        this.idPelicula = idPelicula;
        this.idConsola = idConsola;
        this.nroSocio = nroSocio;
    }

    public Alquiler(){
        this.idPelicula=" ";
        this.idConsola = " ";
        this.nroSocio = 0;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alquiler)) return false;
        Alquiler alquiler = (Alquiler) o;
        return idPelicula.equals(alquiler.idPelicula) && idConsola.equals(alquiler.idConsola) && nroSocio.equals(alquiler.nroSocio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelicula, idConsola, nroSocio);
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getIdConsola() {
        return idConsola;
    }

    public void setIdConsola(String idConsola) {
        this.idConsola = idConsola;
    }

    public Integer getNroSocio() {
        return nroSocio;
    }

    public void setNroSocio(Integer nroSocio) {
        this.nroSocio = nroSocio;
    }
}
