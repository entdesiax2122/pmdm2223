package com.dam.android.repositorios;

import com.dam.android.modelos.Actividad;
import com.dam.android.modelos.Departamento;

import java.util.List;

public interface ActividadRepositorio extends BaseRepositorio<Actividad> {
    List<Actividad> recuperarPorTitulo(String filtro);
    List<Actividad> recuperarPorDepartamento(Departamento d);
}
