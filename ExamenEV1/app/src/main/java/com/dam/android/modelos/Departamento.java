package com.dam.android.modelos;

import java.util.Objects;

public class Departamento {
    private Integer id;
    private String nombre;
    private Boolean activado = true;
    private Boolean eliminado = false;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Departamento(Integer id, String nombre, Boolean activado, Boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.activado = activado;
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivado() {
        return activado;
    }

    public void setActivado(Boolean activado) {
        this.activado = activado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
