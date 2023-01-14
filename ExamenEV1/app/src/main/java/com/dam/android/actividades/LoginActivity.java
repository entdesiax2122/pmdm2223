package com.dam.android.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.android.ProyectoApplication;
import com.dam.android.R;
import com.dam.android.api.IaxApiServices;

public class LoginActivity extends AppCompatActivity {
    EditText etUs;
    EditText etPw;
    Button btnLogin;
    ProgressBar pbLogin;
    TextView tvToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUs = findViewById(R.id.etUs);
        etPw = findViewById(R.id.etPw);
        btnLogin = findViewById(R.id.btnLogin);
        pbLogin = findViewById(R.id.pbLogin);
        tvToken = findViewById(R.id.tvToken);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginTask().execute();
            }
        });
    }

    private class LoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            btnLogin.setVisibility(View.GONE);
            pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean res = Boolean.FALSE;
            String token = IaxApiServices.loginUsuario(etUs.getText().toString(), etPw.getText().toString(), ProyectoApplication.idDispositivo);
            if (token != null) {
                res = Boolean.TRUE;
            }
            return res;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            btnLogin.setVisibility(View.VISIBLE);
            pbLogin.setVisibility(View.GONE);
            if (result.equals(Boolean.TRUE)) {
                String token = ProyectoApplication.loginResponse.getToken();
                tvToken.setText(token);
                Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                //Creamos el Intent
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            } else if (ProyectoApplication.loginResponse != null) {
                tvToken.setText(ProyectoApplication.loginResponse.getResultDesc());
            }
        }
    }



}