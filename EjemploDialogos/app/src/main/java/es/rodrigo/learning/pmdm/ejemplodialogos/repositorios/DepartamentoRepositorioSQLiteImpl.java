package es.rodrigo.learning.pmdm.ejemplodialogos.repositorios;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.rodrigo.learning.pmdm.ejemplodialogos.modelos.Departamento;


public class DepartamentoRepositorioSQLiteImpl implements DepartamentoRepositorio {
    private SQLiteDatabase db;

    public DepartamentoRepositorioSQLiteImpl(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS departamentos(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL);");

//        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Matemáticas');");
//        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Lengua');");
//        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Biología');");
//        db.execSQL("INSERT INTO departamentos(nombre) VALUES ('Informática');");
    }

    @Override
    public Integer guardar(Departamento d) {
        if (d.getId() == null) {
            db.execSQL("INSERT INTO departamentos(nombre) " +
                       "VALUES ('" + d.getNombre() + "');");
            Integer id = getMaxId();
            d.setId(id);
        } else {
            db.execSQL("UPDATE departamentos SET nombre='" + d.getNombre()
                    + "' WHERE id=" + d.getId() + ";");
        }
        return d.getId();
    }


    private Integer getMaxId() {
        Integer res = null;
        Cursor c = db.rawQuery("SELECT MAX(id) FROM departamentos",
                null);

        if (c.getCount()>0) {
            while (c.moveToNext()) {
                res = c.getInt(0);
            }
        }
        return res;
    }



    @Override
    public Integer eliminar(Departamento d) {
        Integer res  = null;
        if (d.getId() != null) {
            db.execSQL("DELETE FROM departamentos " +
                       "WHERE id=" + d.getId() + ";");
            res = d.getId();
        }
        return res;
    }

    @Override
    public Departamento recuperar(Integer id) {
        Departamento encontrado = null;
        String query="SELECT * FROM departamentos WHERE id = ?";
        String[] selectionArgs = {id.toString()};
        Cursor c = db.rawQuery(query, selectionArgs);
        if (c.getCount()>0) {
            while (c.moveToNext()) {
                encontrado = new Departamento(c.getString(1));
                encontrado.setId(c.getInt(0));
            }
        }
        c.close();
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

    @Override
    public List<Departamento> buscarPorNombre(String filtro) {
        ArrayList<Departamento> lista = new ArrayList<>();

        String[] selectionArgs = {"%" + filtro + "%"};
        Cursor c = db.rawQuery("SELECT * FROM departamentos WHERE nombre LIKE ?", selectionArgs);
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
