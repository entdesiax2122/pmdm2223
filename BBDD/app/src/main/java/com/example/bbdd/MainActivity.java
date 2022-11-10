package com.example.bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText txtGrupo, txtDisco;
    ListView listaDiscos;
    TextView txtResultado;
    SQLiteDatabase db;
    Button btnAnadir, btnBorrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGrupo=(EditText)findViewById(R.id.txtGrupo);
        txtDisco=(EditText)findViewById(R.id.txtDisco);
        listaDiscos=(ListView)findViewById(R.id.ListaDiscos);
        txtResultado=(TextView)findViewById(R.id.txtResultado);
        btnAnadir=(Button)findViewById(R.id.anadir);
        btnBorrar=(Button)findViewById(R.id.borrar);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anadir(v);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Borrar(v);
            }
        });

        //db.execSQL("DROP DATABASE MisDiscos");
        db=openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misDiscos(Grupo VARCHAR, Disco Varchar);");
//        db.execSQL("DELETE FROM misDiscos");
//        db.execSQL("INSERT INTO misDiscos VALUES ('SA','Ratas');");
//        db.execSQL("INSERT INTO misDiscos VALUES ('Reincidente','Algazara');");
        Listar();
    }


    public void Anadir(View v)
    {
        String gr = txtGrupo.getText().toString();
        String dis = txtDisco.getText().toString();

        db.execSQL("INSERT INTO misDiscos VALUES ('"+gr+"','"+dis+"');");
        txtGrupo.setText("");

        txtDisco.setText("");
        Toast toast = Toast.makeText(this,"Se añadió el disco "+dis+" del grupo "+gr, Toast.LENGTH_LONG);
        toast.show();

        Listar();


    }

    public void Borrar(View v)
    {
        String gr = txtGrupo.getText().toString();
        String dis = txtDisco.getText().toString();

        try {
            db.execSQL("DELETE FROM misDiscos WHERE Grupo ='" + gr + "' or Disco = '" + dis + "';");
            txtGrupo.setText("");

            txtDisco.setText("");
            Toast toast = Toast.makeText(this, "Se borró el disco " + dis + " del grupo " + gr, Toast.LENGTH_LONG);
            toast.show();
        }catch (SQLException s){
            Toast toast = Toast.makeText(this, "Error al borrar", Toast.LENGTH_LONG);
            toast.show();


        }
        Listar();


    }




    public void Listar()
    {

        // ArrayAdapter<String> adaptador;
        //List<String> lista = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM misDiscos", null);

        txtResultado.setText("");
        if (c.getCount()==0)
            txtResultado.setText("No hay registros");
        else {
            while (c.moveToNext())
                txtResultado.append(" " + c.getString(0) + " - " + c.getString(1) + "\n");
        }

        c.close();



    }


}
