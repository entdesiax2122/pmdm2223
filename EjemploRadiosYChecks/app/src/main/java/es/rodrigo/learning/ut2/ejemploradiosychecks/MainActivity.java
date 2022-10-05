package es.rodrigo.learning.ut2.ejemploradiosychecks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView respuesta, ccc;
    private RadioButton rbOpcion1;
    private RadioButton rbOpcion2;
    private Button btnAceptar, btnCaja;
    private CheckBox chkb;
    private Toast mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respuesta = (TextView)findViewById(R.id.Respuesta);
        rbOpcion1 = (RadioButton)findViewById(R.id.RbOpcion1);
        rbOpcion2 = (RadioButton)findViewById(R.id.RbOpcion2);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnCaja = (Button)findViewById(R.id.btnCaja);
        ccc=(TextView)findViewById(R.id.chk);
        chkb=(CheckBox)findViewById(R.id.si);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rbOpcion1.isChecked())
                    respuesta.setText("Está seleccionada la opción 1");
                else
                    respuesta.setText("Está seleccionada la opción 2");

            }
        });

        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcion = "";
                switch(view.getId()) {
                    case R.id.RbOpcion1:
                        opcion = "opción 1";
                        break;
                    case R.id.RbOpcion2:
                        opcion = "opción 2";
                        break;
                }

                respuesta.setText("La opción seleccionada: " + opcion);
            }
        };
        rbOpcion1.setOnClickListener(list);
        rbOpcion2.setOnClickListener(list);


        btnCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chkb.isChecked())
                    ccc.setText("Marcado");

            }
        });

        chkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox)v).isChecked();

                if (isChecked) {
                    chkb.setText("Checkbox marcado!");
                }
                else {
                    chkb.setText("Checkbox desmarcado!");
                }


            }
        });

    }
}