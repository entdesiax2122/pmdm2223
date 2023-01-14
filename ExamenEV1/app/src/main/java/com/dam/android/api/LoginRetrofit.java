package com.dam.android.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface LoginRetrofit {
    @PATCH("/api/users/loginnewdevice")
    Call<LoginResponse> patchLogin(@Body LoginRequest loginRequest);
}
