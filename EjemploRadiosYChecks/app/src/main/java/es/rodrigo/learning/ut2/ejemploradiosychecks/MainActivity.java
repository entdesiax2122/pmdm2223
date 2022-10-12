package es.rodrigo.learning.ut2.ejemploradiosychecks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView respuesta, chk;
    private RadioButton rbOpcion1;
    private RadioButton rbOpcion2;
    private Button btnAceptar;


    private Button btnCaja;
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
        chkb=(CheckBox)findViewById(R.id.si);

        chk = (TextView) findViewById(R.id.chk);
        chk.setVisibility(View.GONE);



        // Trabajando sobre el evento de otro widget, pero
        // evaluamos isChecked de los RadioButton.
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbOpcion1.isChecked())
                    respuesta.setText("Está seleccionada la opción 1");
                else
                    respuesta.setText("Está seleccionada la opción 2");
            }
        });

        // Ahora, sin embargo, preguntamos por el id de la vista asociada
        // a este evento (el view que llega en la llamada al onClick)
        View.OnClickListener myOnClickAction = new View.OnClickListener() {
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
        // Estamos añadiendo un mismo método listener para el
        // evento onClick de todos los RadioButton.
        rbOpcion1.setOnClickListener(myOnClickAction);
        rbOpcion2.setOnClickListener(myOnClickAction);


        btnCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chkb.isChecked())
                    chk.setText("Marcado");

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
                    chkb.setVisibility(View.VISIBLE);
                }


            }
        });

    }
}