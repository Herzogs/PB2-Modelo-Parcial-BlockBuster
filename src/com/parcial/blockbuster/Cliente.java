package com.parcial.blockbuster;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {

    private Integer nroSocio;
    private String apeNom;
    private Integer edad;

    public Cliente(Integer nroSocio, String apeNom, Integer edad) {
        this.nroSocio = nroSocio;
        this.apeNom = apeNom;
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return nroSocio.equals(cliente.nroSocio) && apeNom.equals(cliente.apeNom) && edad.equals(cliente.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroSocio, apeNom, edad);
    }

    @Override
    public int compareTo(Cliente o) {
        return this.nroSocio-o.nroSocio;
    }

    public Integer getNroSocio() {
        return nroSocio;
    }

    public void setNroSocio(Integer nroSocio) {
        this.nroSocio = nroSocio;
    }

    public String getApeNom() {
        return apeNom;
    }

    public void setApeNom(String apeNom) {
        this.apeNom = apeNom;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
