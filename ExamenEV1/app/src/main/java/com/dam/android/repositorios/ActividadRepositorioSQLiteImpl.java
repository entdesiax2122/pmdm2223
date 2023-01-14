package com.dam.android.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dam.android.ProyectoApplication;
import com.dam.android.modelos.Actividad;
import com.dam.android.modelos.Departamento;

import java.util.ArrayList;
import java.util.List;

public class ActividadRepositorioSQLiteImpl implements ActividadRepositorio {
    private SQLiteDatabase db;
    private String tabla = "actividades";

    public ActividadRepositorioSQLiteImpl(SQLiteDatabase db) {
        this.db = db;
//        db.execSQL("DROP TABLE "+tabla);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ tabla +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT NOT NULL, " +
                "iddepto INTEGER, " +
                "lugar TEXT); ");

    }

    @Override
    public Integer guardar(Actividad activ) {
        ContentValues values = new ContentValues();
        values.put("titulo", activ.getTitulo());
        if (activ.getDepartamento() != null) {
            values.put("iddepto", activ.getDepartamento().getId());
        }
        values.put("lugar", activ.getLugar());

        if (activ.getId() == null) {
            db.insert(tabla, null, values);
            Integer id = getMaxId();
            activ.setId(id);
        } else {
            db.update(tabla, values, "id = ?",new String[]{activ.getId()+""});
        }
        return activ.getId();
    }


    private Integer getMaxId() {
        Integer res = null;
        Cursor c = db.rawQuery("SELECT MAX(id) FROM " + tabla,
                null);

        if (c.getCount()>0) {
            while (c.moveToNext()) {
                res = c.getInt(0);
            }
        }
        return res;
    }



    @Override
    public Integer eliminar(Actividad u) {
        Integer res  = null;
        if (u.getId() != null) {
            db.execSQL("DELETE FROM " + tabla +
                    " WHERE id=" + u.getId() + ";");
            res = u.getId();
        }
        return res;
    }

    @Override
    public Actividad recuperar(Integer id) {
        Actividad activ = null;
        String query="SELECT * FROM " + tabla + " WHERE id = ?";
        String[] selectionArgs = {id.toString()};
        Cursor c = db.rawQuery(query, selectionArgs);
        if (c.getCount()>0) {
            while (c.moveToNext()) {
                Departamento d = ProyectoApplication.getDepartamentoRepositorio().recuperar(c.getInt(2));
                activ = new Actividad(c.getString(1), d, c.getString(3));
                activ.setId(c.getInt(0));
            }
        }
        c.close();
        return activ;
    }

    @Override
    public ArrayList<Actividad> recuperarTodos() {
        ArrayList<Actividad> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + tabla, null);
        if (c.getCount()>0) {
            while (c.moveToNext()) {
                Departamento d = ProyectoApplication.getDepartamentoRepositorio().recuperar(c.getInt(2));
                Actividad activ = new Actividad(c.getString(1), d, c.getString(3));
                activ.setId(c.getInt(0));
                lista.add(activ);
            }
        }
        c.close();
        return lista;
    }

    @Override
    public List<Actividad> recuperarPorTitulo(String filtro) {
        ArrayList<Actividad> lista = new ArrayList<>();
        filtro = "%" + filtro + "%";
        String query="SELECT * FROM " + tabla + " WHERE titulo LIKE ?";
        String[] selectionArgs = {filtro};
        Cursor c = db.rawQuery(query, selectionArgs);

        if (c.getCount()>0) {
            while (c.moveToNext()) {
                Departamento d = ProyectoApplication.getDepartamentoRepositorio().recuperar(c.getInt(2));
                Actividad activ = new Actividad(c.getString(1), d, c.getString(3));
                activ.setId(c.getInt(0));
                lista.add(activ);
            }
        }
        c.close();
        return lista;
    }

    @Override
    public List<Actividad> recuperarPorDepartamento(Departamento dptofilt) {
        ArrayList<Actividad> lista = new ArrayList<>();
        String query="SELECT * FROM " + tabla + " WHERE iddepto = ?";
        String[] selectionArgs = {dptofilt.getId().toString()};
        Cursor c = db.rawQuery(query, selectionArgs);
        if (c.getCount()>0) {
            while (c.moveToNext()) {
                Departamento d = ProyectoApplication.getDepartamentoRepositorio().recuperar(c.getInt(2));
                Actividad activ = new Actividad(c.getString(1), d, c.getString(3));
                activ.setId(c.getInt(0));
                lista.add(activ);
            }
        }
        c.close();
        return lista;
    }
}
