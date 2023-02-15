package es.rodrigo.learning.pmdm.ejemplodialogos.repositorios;


import java.util.List;

import es.rodrigo.learning.pmdm.ejemplodialogos.modelos.Departamento;

public interface DepartamentoRepositorio extends BaseRepositorio<Departamento> {
    List<Departamento> buscarPorNombre(String filtro);
}
