package com.example.pr_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView tvMensaje, tvPosicion;
    private Spinner spOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = (TextView)findViewById(R.id.tvMensaje);
        tvPosicion = (TextView)findViewById(R.id.tvPosicion);
        spOpciones = (Spinner)findViewById(R.id.spOpciones);

        //Alternativa 2: Recurso XML de tipo string-array
//        ArrayAdapter<CharSequence> adaptador =
//                ArrayAdapter.createFromResource(this,
//                        R.array.valores_array, android.R.layout.simple_spinner_item);
//        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spOpciones.setAdapter(adaptador);

        // Alternativa 1: Array java
        String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spOpciones.setAdapter(adaptador);

        spOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       android.view.View v, int position, long id) {
                tvMensaje.setText("Seleccionado: " + parent.getItemAtPosition(position));
                tvPosicion.setText("Posicion del Item: " + position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                tvMensaje.setText("");
            }
        });
    }
}