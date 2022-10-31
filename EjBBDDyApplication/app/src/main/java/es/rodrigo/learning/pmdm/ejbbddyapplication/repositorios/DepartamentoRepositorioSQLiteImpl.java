package es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import es.rodrigo.learning.pmdm.ejbbddyapplication.modelos.Departamento;

public class DepartamentoRepositorioSQLiteImpl implements DepartamentoRepositorio {
    private SQLiteDatabase db;

    public DepartamentoRepositorioSQLiteImpl(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS departamentos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL);");

        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Matemáticas');");
        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Lengua');");
        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Biología');");
        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Informática');");
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
        ArrayList<Departamento> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM departamentos", null);

        if (c.getCount()>0) {
            while (c.moveToNext()) {
                Departamento d = new Departamento(c.getString(1));
                d.setId(c.getInt(0));
                lista.add(d);
            }
        }
        c.close();
        return lista;
    }
}
