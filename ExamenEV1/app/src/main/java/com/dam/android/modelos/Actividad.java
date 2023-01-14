package com.dam.android.modelos;

public class Actividad {
    private Integer id;
    private String titulo;
    private Departamento departamento;
    private String lugar;

    public Actividad(String titulo, Departamento departamento, String lugar) {
        this.titulo = titulo;
        this.departamento = departamento;
        this.lugar = lugar;
    }

    public Actividad() {

    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", lugar='" + lugar + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
