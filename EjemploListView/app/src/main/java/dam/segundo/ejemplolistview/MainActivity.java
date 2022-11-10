package dam.segundo.ejemplolistview;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView lvOpciones;
    private TextView tvMensaje;
    private EditText etNuevo;
    private Button btNuevo;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvOpciones = (ListView) findViewById(R.id.lvOpciones);
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        etNuevo = (EditText) findViewById(R.id.etNuevo);
        btNuevo = (Button) findViewById(R.id.btNuevo);

//        final String[] datos =
//                new String[]{"Elem1", "Elem2", "Elem3", "Elem4", "Elem5"};
        lista = new ArrayList<>();
        lista.add("Elem1");
        lista.add("Elem2");
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, lista);
        lvOpciones.setAdapter(adaptador);

        btNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etNuevo.getText())) {
                    // añadimos
                    lista.add(etNuevo.getText().toString());
                    etNuevo.setText("");
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Por favor pon algo", Toast.LENGTH_SHORT).show();
                }
            }
        });


        lvOpciones.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent,
                                        android.view.View v, int position, long id) {
                    tvMensaje.setText("Seleccionado: " + parent.getItemAtPosition(position));
                }
            }
        );


    }
}