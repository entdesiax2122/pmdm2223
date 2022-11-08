package es.rodrigo.learning.pmdm.ejemplodialogos.repositorios;

import java.util.ArrayList;

import es.rodrigo.learning.pmdm.ejbbddyapplication.modelos.Departamento;

public class DepartamentoRepositorioListImpl implements DepartamentoRepositorio {
    private ArrayList<Departamento> lista;
    private static Integer sigteId = 0;

    public DepartamentoRepositorioListImpl() {
        this.lista = new ArrayList<>();
    }

    @Override
    public Integer guardar(Departamento d) {
        if (d.getId() == null) {
            d.setId(++sigteId);
        }
        lista.add(d);
        return d.getId();
    }

    @Override
    public Integer eliminar(Departamento d) {
        lista.remove(d);
        return d.getId();
    }

    @Override
    public Departamento recuperar(Integer id) {
        Departamento encontrado = null;
        for(Departamento d : lista) {
            if (d.getId().compareTo(id) == 0) {
                encontrado = d;
            }
        }
        return encontrado;
    }

    @Override
    public ArrayList<Departamento> recuperarTodos() {
        return lista;
    }
}
