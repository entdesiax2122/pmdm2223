package com.dam.android.api;

import com.dam.android.modelos.Usuario;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private Long id;
    private String token;
    private String resultCode;
    private String resultDesc;
    private Usuario usuarioMobEnt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Usuario getUsuarioMobEnt() {
        return usuarioMobEnt;
    }

    public void setUsuarioMobEnt(Usuario usuarioMobEnt) {
        this.usuarioMobEnt = usuarioMobEnt;
    }
}
