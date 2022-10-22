package es.rodrigo.learning.pmdm.ejemplolistview3.actividades;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import es.rodrigo.learning.pmdm.ejemplolistview3.R;
import es.rodrigo.learning.pmdm.ejemplolistview3.modelos.Departamento;
import es.rodrigo.learning.pmdm.ejemplolistview3.repositorios.DepartamentoRepositorio;
import es.rodrigo.learning.pmdm.ejemplolistview3.repositorios.DepartamentoRepositorioListImpl;
import es.rodrigo.learning.pmdm.ejemplolistview3.util.Utilidades;

public class MainActivity extends Activity {
    private DepartamentoRepositorio departamentoRepositorio;
    private Button btNuevoDepto;
    private EditText etNombre;
    private ListView lvDeptos;
    private List<Departamento> lista;
    private ArrayAdapter adaptadorDeptos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departamentoRepositorio = DepartamentoRepositorioListImpl.getInstancia();
        btNuevoDepto = (Button) findViewById(R.id.btNuevoDepto);
        etNombre = (EditText) findViewById(R.id.etNombre);
        lvDeptos = (ListView) findViewById(R.id.lvDeptos);

        lista = departamentoRepositorio.recuperarTodos();
        adaptadorDeptos = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, lista) {
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(lista.get(position).getId() + " - " + lista.get(position).getNombre());
                return view;
            }
        };
        lvDeptos.setAdapter(adaptadorDeptos);

        btNuevoDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utilidades.validarCampoObligatorio(etNombre, getString(R.string.campo_obligatorio))) {
                    Departamento d = new Departamento(etNombre.getText().toString());
                    etNombre.setText("");
                    departamentoRepositorio.guardar(d);
                    lista = departamentoRepositorio.recuperarTodos();
                    adaptadorDeptos.notifyDataSetChanged();
                }
            }
        });
    }
}