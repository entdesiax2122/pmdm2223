package es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.rodrigo.learning.pmdm.ejbbddyapplication.modelos.Departamento;

public class DepartamentoRepositorioSQLiteImpl implements DepartamentoRepositorio {
    private SQLiteDatabase db;

    public DepartamentoRepositorioSQLiteImpl(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS departamentos(id INTEGER, nombre TEXT);");
    }

    @Override
    public Integer guardar(Departamento d) {
        return null;
    }

    @Override
    public Integer eliminar(Departamento d) {
        return null;
    }

    @Override
    public Departamento recuperar(Integer id) {
        Departamento encontrado = null;

        return encontrado;
    }

    @Override
    public ArrayList<Departamento> recuperarTodos() {
        return null;
    }
}
