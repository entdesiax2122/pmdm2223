package com.dam.android.repositorios;

import com.dam.android.modelos.Departamento;

import java.util.ArrayList;

public class DepartamentoRepositorioListImpl implements DepartamentoRepositorio {
    private static DepartamentoRepositorioListImpl instancia;
    private ArrayList<Departamento> lista;
    private static Integer sigteId = 0;

    public DepartamentoRepositorioListImpl() {
        this.lista = new ArrayList<>();
    }

    public static DepartamentoRepositorioListImpl getInstancia() {
        if (instancia == null) {
            instancia = new DepartamentoRepositorioListImpl();
        }
        return instancia;
    }

    @Override
    public Integer guardar(Departamento d) {
        if (d.getId() == null) {
            d.setId(++sigteId);
            lista.add(d);
        }
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
