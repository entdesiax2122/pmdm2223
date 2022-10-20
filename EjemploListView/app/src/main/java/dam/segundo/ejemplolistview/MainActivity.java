package dam.segundo.ejemplolistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ListView lvOpciones;
    private TextView tvMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvOpciones = (ListView) findViewById(R.id.lvOpciones);
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);

        final String[] datos =
                new String[]{"Elem1", "Elem2", "Elem3", "Elem4", "Elem5"};
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_2, datos);
        lvOpciones.setAdapter(adaptador);


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