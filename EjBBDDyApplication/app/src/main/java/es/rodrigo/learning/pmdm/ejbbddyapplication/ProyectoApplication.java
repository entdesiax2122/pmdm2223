package es.rodrigo.learning.pmdm.ejbbddyapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios.DepartamentoRepositorio;
import es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios.DepartamentoRepositorioListImpl;
import es.rodrigo.learning.pmdm.ejbbddyapplication.repositorios.DepartamentoRepositorioSQLiteImpl;

public class ProyectoApplication extends Application {
    private static DepartamentoRepositorio departamentoRepositorio;
    private static SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        db=openOrCreateDatabase("ExtraescolaresDB", Context.MODE_PRIVATE, null);
    }

    public static DepartamentoRepositorio getDepartamentoRepositorio() {
        if (departamentoRepositorio == null) {
            // aquí decidimos qué implementación queremos usar
            departamentoRepositorio = new DepartamentoRepositorioSQLiteImpl(db);
//            departamentoRepositorio = new DepartamentoRepositorioListImpl();
        }
        return departamentoRepositorio;
    }
}
