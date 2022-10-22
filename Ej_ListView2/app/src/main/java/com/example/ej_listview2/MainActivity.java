package com.example.ej_listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lstOpciones;
    private TextView lblMensaje;
    private EditText lblNombre;
    private Button btnAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList listaUsuario=new ArrayList<String>();

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, listaUsuario);


        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        lblMensaje=(TextView)findViewById(R.id.lblMensaje);
        lblNombre=(EditText)findViewById(R.id.lblNombre);
        btnAceptar=(Button) findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                String nom=lblNombre.getText().toString();
                listaUsuario.add(nom);
            }
        });

        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " +
                                parent.getItemAtPosition(position));
                    }

                });


    }
}