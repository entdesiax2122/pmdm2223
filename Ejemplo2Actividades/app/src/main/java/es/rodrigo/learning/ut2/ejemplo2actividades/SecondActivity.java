package es.rodrigo.learning.ut2.ejemplo2actividades;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView txtResultado;
    private Integer par,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Localizar los controles
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        par=bundle.getInt("Numero");
        res=par%2;

        if (res==0)
            txtResultado.setText("el número " + par + " es par");
        else
            txtResultado.setText("el número " + par + " es impar");

    }

}