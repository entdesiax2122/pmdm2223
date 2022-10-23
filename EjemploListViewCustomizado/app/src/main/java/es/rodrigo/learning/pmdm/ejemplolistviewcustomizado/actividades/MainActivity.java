package es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.actividades;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.R;
import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.adaptadores.DepartamentoAdapter;
import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.modelos.Departamento;
import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.repositorios.DepartamentoRepositorio;
import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.repositorios.DepartamentoRepositorioListImpl;
import es.rodrigo.learning.pmdm.ejemplolistviewcustomizado.util.Utilidades;

public class MainActivity extends Activity {
    private DepartamentoRepositorio departamentoRepositorio;
    private Button btNuevoDepto;
    private EditText etNombre;
    private ListView lvDeptos;
    private List<Departamento> lista;
    private DepartamentoAdapter adaptadorDeptos;
    private View oldViewGround;
    private int indiceDepartamento = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departamentoRepositorio = DepartamentoRepositorioListImpl.getInstancia();
        btNuevoDepto = (Button) findViewById(R.id.btNuevoDepto);
        etNombre = (EditText) findViewById(R.id.etNombre);
        lvDeptos = (ListView) findViewById(R.id.lvDeptos);

        // preparar y cargar listview de departamentos
        // añadimos la cabecera
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.departamento_list_header, lvDeptos, false);
        if (lvDeptos.getHeaderViewsCount() == 0) {
            lvDeptos.addHeaderView(header);
        }
        // cargamos la lista de departamentos
        lista = departamentoRepositorio.recuperarTodos();
        // creamos el Adaptador personalizado y se lo añadimos al ListView
        adaptadorDeptos = new DepartamentoAdapter(getBaseContext(), lista);
        lvDeptos.setAdapter(adaptadorDeptos);
        // programamos el AdapterView.OnItemClickListener
        lvDeptos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    indiceDepartamento = position - 1;
                    if (oldViewGround != null && view != oldViewGround) {
                        oldViewGround.setBackgroundResource(R.drawable.recuadro_list_item);
                    }
                    view.setBackgroundColor(getResources().getColor(R.color.amber));
                    oldViewGround = view;
                }
            }
        });
        // fin preparación listview de departamentos
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