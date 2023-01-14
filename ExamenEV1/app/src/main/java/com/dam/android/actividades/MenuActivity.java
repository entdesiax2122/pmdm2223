package com.dam.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dam.android.ProyectoApplication;
import com.dam.android.R;
import com.dam.android.dialogos.SimpleInfoDialog;
import com.dam.android.dialogos.SimpleInfoOkBtnDialog;
import com.dam.android.modelos.Departamento;
import com.dam.android.util.Utilidades;

import java.util.List;

public class MenuActivity extends Activity {
    private Button btnSimpleInfo;
    private Button btnSimpleInfoOkBtn;
    private Button btnDeptos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // bindear componentes
        btnSimpleInfo = findViewById(R.id.btnSimpleInfo);
        btnSimpleInfoOkBtn = findViewById(R.id.btnSimpleInfoOkBtn);
        btnDeptos = findViewById(R.id.btnDeptos);

        btnSimpleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoDialog sid = new SimpleInfoDialog();
                sid.setConfiguration("Título ejemplo","Mensaje de ejemplo que se muestra dentro del diálogo");
                sid.show(getFragmentManager(),"Mi diálogo simple");
            }
        });

        btnSimpleInfoOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoOkBtnDialog siobd = new SimpleInfoOkBtnDialog();
                siobd.setConfiguration("Título", "Mensaje", "Ok", null);
                siobd.show(getFragmentManager(), "Mi diálogo simple con botón aceptar");
            }
        });

        btnDeptos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos el Intent
                Intent intent = new Intent(MenuActivity.this, DepartamentosActivity.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }
}