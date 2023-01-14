package com.dam.android.modelos;

import java.util.Objects;

public class Usuario {
    private Integer id;
    private String email;
    private String password;
    private String dni;
    private Boolean activado = true;
    private Boolean eliminado = false;
    private Boolean aceptacondicioneslegales = false;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private Integer nivel;
    private String telefono;
    private String empresa;
    private String direccion;
    private String rol;
    private Integer deptoId;
    private String idDispositivo = "";
    private Departamento departamento;

    public Usuario(String nombre, String apellidos, String email, String password, String dni, String empresa, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.empresa = empresa;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String apellidos, String email, String passwd, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = passwd;
        this.dni = dni;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getDeptoId() {
        return deptoId;
    }

    public void setDeptoId(Integer deptoId) {
        this.deptoId = deptoId;
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

    public Boolean getAceptacondicioneslegales() {
        return aceptacondicioneslegales;
    }

    public void setAceptacondicioneslegales(Boolean aceptacondicioneslegales) {
        this.aceptacondicioneslegales = aceptacondicioneslegales;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getNombreCompleto() {
        String nombreUsuario = "";
        if (this.getNombre() != null) {
            nombreUsuario += this.getNombre();
            if (this.getApellidos() != null) {
                nombreUsuario += " " + this.getApellidos();
            }
        }
        return nombreUsuario;
    }


    @Override
    public String toString() {
        return getNombreCompleto();
    }
}
