package com.example.ejfragdinamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button verde, rojo, azul;
    FragmentAzul frAzul;
    FragmentRojo frRojo;
    FragmentVerde frVerde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verde=findViewById(R.id.btnVerde);
        azul=findViewById(R.id.btAzul);
        rojo=findViewById(R.id.btnRojo);

        frAzul=new FragmentAzul();
        frRojo=new FragmentRojo();
        frVerde = new FragmentVerde();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,frAzul).commit();

        verde.setOnClickListener(this);
        azul.setOnClickListener(this);
        rojo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        switch(view.getId()){

            case R.id.btAzul:
                transaction.replace(R.id.contenedor,frAzul);
                break;

            case R.id.btnVerde:
                transaction.replace(R.id.contenedor,frVerde);
                break;

            case R.id.btnRojo:
                transaction.replace(R.id.contenedor,frRojo);
                break;
        }

        transaction.commit();
    }
}