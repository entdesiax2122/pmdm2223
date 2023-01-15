package com.dam.android.api;

import com.dam.android.modelos.Departamento;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DepartamentoRetrofit {
    @GET("/api/departamentos")
    Call<ArrayList<Departamento>> getDepartamentos(@Header("Authorization") String token);

    @POST("/api/departamentos")
    Call<Departamento> postDepartamento(@Header("Authorization") String token,
                                        @Body Departamento departamento);
}
