package com.dam.android.api;

import com.dam.android.ProyectoApplication;

import java.io.IOException;

public class IaxApiServices {
    public static String loginUsuario(String email, String password, String idDispositivo) {
        String token = null;
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        loginRequest.setIdDispositivo(idDispositivo);

        LoginResponse loginResponse = null;

        try {
            loginResponse = ProyectoApplication.getLoginRetrofit().patchLogin(loginRequest)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (loginResponse != null) {
            ProyectoApplication.loginResponse = loginResponse;
            if (loginResponse.getResultCode().equals("OK")) {
                // ahora ya podemos guardar el usuario
                ProyectoApplication.usuarioApp = loginResponse.getUsuarioMobEnt();
                token = loginResponse.getToken();
            }
        }

        return token;
    }
}
