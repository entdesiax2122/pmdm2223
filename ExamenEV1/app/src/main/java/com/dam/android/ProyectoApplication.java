package com.dam.android;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dam.android.api.LoginResponse;
import com.dam.android.api.LoginRetrofit;
import com.dam.android.modelos.Usuario;
import com.dam.android.repositorios.ActividadRepositorio;
import com.dam.android.repositorios.ActividadRepositorioSQLiteImpl;
import com.dam.android.repositorios.DepartamentoRepositorio;
import com.dam.android.repositorios.DepartamentoRepositorioSQLiteImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProyectoApplication extends Application {
    public static String idDispositivo = "12112134124";
    private static DepartamentoRepositorio departamentoRepositorio;
    private static ActividadRepositorio actividadRepositorio;
    private static SQLiteDatabase db;
    private static Retrofit retrofit;
    private static final String BASE_URL_API = "http://34.175.24.197:8080/"; // PROD
    private static LoginRetrofit loginRetrofit;
    public static LoginResponse loginResponse;
    public static Usuario usuarioApp;

    @Override
    public void onCreate() {
        super.onCreate();

        db=openOrCreateDatabase("ExtraescolaresDB", Context.MODE_PRIVATE, null);

        configurarRetrofit();
    }

    public static DepartamentoRepositorio getDepartamentoRepositorio() {
        if (departamentoRepositorio == null) {
            // aquí decidimos qué implementación queremos usar
            departamentoRepositorio = new DepartamentoRepositorioSQLiteImpl(db);
            // departamentoRepositorio = new DepartamentoRepositorioListImpl();
        }
        return departamentoRepositorio;
    }

    public static ActividadRepositorio getActividadRepositorio() {
        if (actividadRepositorio == null) {
            actividadRepositorio = new ActividadRepositorioSQLiteImpl(db);
        }
        return actividadRepositorio;
    }

    private void configurarRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loginRetrofit = retrofit.create(LoginRetrofit.class);
    }

    public static LoginRetrofit getLoginRetrofit() {
        return loginRetrofit;
    }

}
