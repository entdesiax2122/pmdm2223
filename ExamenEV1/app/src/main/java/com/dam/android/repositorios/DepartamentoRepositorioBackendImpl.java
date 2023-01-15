package com.dam.android.repositorios;

import com.dam.android.ProyectoApplication;
import com.dam.android.modelos.Departamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DepartamentoRepositorioBackendImpl implements DepartamentoRepositorio {
    @Override
    public Integer guardar(Departamento departamento) {
        Integer res = null;

        try {
            Response<Departamento> response = ProyectoApplication.getDepartamentoRetrofit().
                    postDepartamento(ProyectoApplication.loginResponse.getToken(), departamento).execute();
            if(response != null && response.code() == 200) {
                departamento = response.body();
                res = departamento.getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Integer eliminar(Departamento obj) {
        return null;
    }

    @Override
    public Departamento recuperar(Integer id) {
        return null;
    }

    @Override
    public List<Departamento> recuperarTodos() {
        List<Departamento> res = null;

        try {
            Response<ArrayList<Departamento>> resp = ProyectoApplication.getDepartamentoRetrofit().getDepartamentos(ProyectoApplication.loginResponse.getToken()).execute();
            if (resp != null) {
                res = resp.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
