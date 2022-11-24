package es.rodrigo.learning.pmdm.ejemplodialogos.actividades;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.rodrigo.learning.pmdm.ejemplodialogos.ProyectoApplication;
import es.rodrigo.learning.pmdm.ejemplodialogos.R;
import es.rodrigo.learning.pmdm.ejemplodialogos.adaptadores.DepartamentoAdapter;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.InputBoxDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.OnSubmitSimpleListener;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.SimpleInfoOkBtnDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.OkCancelDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.modelos.Departamento;
import es.rodrigo.learning.pmdm.ejemplodialogos.repositorios.DepartamentoRepositorio;
import es.rodrigo.learning.pmdm.ejemplodialogos.util.Utilidades;

public class DepartamentosActivity extends AppCompatActivity {
    private DepartamentoRepositorio departamentoRepositorio;
    private Button btNuevoDepto;
    private EditText etNombre;
    private ListView lvDeptos;
    private List<Departamento> lista;
    private DepartamentoAdapter adaptadorDeptos;
    private View oldViewGround;
    private int indiceDepartamento = -1;

    // Inicio métodos necesarios para manejar NavigateUp y OptionsMenu.
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sobrenosotros:
                SimpleInfoOkBtnDialog dialog = new SimpleInfoOkBtnDialog();
                dialog.setConfiguration("IAX Sobre nosotros", "Esta es la App para la gestión de actividades extraescolares en el IES Alfonso X el Sabio de Murcia. 2022-2023 Todos los derechos reservados",
                        "Aceptar", null);
                dialog.show(getFragmentManager(), "dialogo_sobre_nosotros");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Fin crear menú de opciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        // Ini ActionBar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iax_logo);
        getSupportActionBar().setTitle(getString(R.string.iax_title));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        // Fin ActionBar
        


        departamentoRepositorio =
                ProyectoApplication.getDepartamentoRepositorio();
        btNuevoDepto = (Button) findViewById(R.id.btNuevoDepto);
        etNombre = (EditText) findViewById(R.id.etNombre);
        lvDeptos = (ListView) findViewById(R.id.lvDeptos);

        // preparar y cargar listview de departamentos
        // añadimos la cabecera
        ViewGroup header = (ViewGroup) getLayoutInflater().
                inflate(R.layout.departamento_list_header,
                lvDeptos, false);
        if (lvDeptos.getHeaderViewsCount() == 0) {
            lvDeptos.addHeaderView(header);
        }
        // cargamos la lista de departamentos
        lista = departamentoRepositorio.recuperarTodos();
        // creamos el Adaptador personalizado y se lo añadimos al ListView
        adaptadorDeptos = new DepartamentoAdapter(getBaseContext(), lista, new OnSubmitSimpleListener() {
            @Override
            public void submit(Object obj) {
                borrarDepto((View) obj);
            }
        }, new OnSubmitSimpleListener() {
            @Override
            public void submit(Object obj) {
                editarDepto((View) obj);
            }
        });
        lvDeptos.setAdapter(adaptadorDeptos);

        // programamos el AdapterView.OnItemClickListener
        lvDeptos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l) {
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
                    adaptadorDeptos.setListDepartamentos(lista);
                    adaptadorDeptos.notifyDataSetChanged();
                }
            }
        });
    }

    public void editarDepto(View view) {
        indiceDepartamento = adaptadorDeptos.getAdapterItemPosition(view);
        if (indiceDepartamento != -1 && adaptadorDeptos.getCount() > 0) {
            Departamento d = lista.get(indiceDepartamento);
            InputBoxDialog dialog = new InputBoxDialog();
            dialog.setConfiguration(d.getNombre(), "Introduzca el nuevo nombre para el departamento",
                    "Aceptar", "Cancelar",
                    new OnSubmitSimpleListener() {
                        @Override
                        public void submit(Object result) {
                            d.setNombre((String) result);
                            departamentoRepositorio.guardar(d);
                            lista = departamentoRepositorio.recuperarTodos();
                            adaptadorDeptos.setListDepartamentos(lista);
                            adaptadorDeptos.notifyDataSetChanged();
                            Toast.makeText(DepartamentosActivity.this, "Departamento actualizado ...", Toast.LENGTH_SHORT).show();
                        }
                    },
                    null
                    );
            dialog.show(getFragmentManager(), "dialogoEditarDepto");
        }
    }

    public void borrarDepto(View view) {
        OkCancelDialog dialog = new OkCancelDialog();
        dialog.setConfiguration("Borrar departamento", "¿Estás seguro de borrar el departamento?",
                "Aceptar", "Cancelar",
                new OnSubmitSimpleListener() {
                    @Override
                    public void submit(Object result) {
                        // positive listener
                        indiceDepartamento = adaptadorDeptos.getAdapterItemPosition(view);
                        if (indiceDepartamento != -1 && adaptadorDeptos.getCount() > 0) {
                            Departamento d = lista.get(indiceDepartamento);
                            departamentoRepositorio.eliminar(d);
                            SimpleInfoOkBtnDialog info = new SimpleInfoOkBtnDialog();
                            info.setConfiguration("Depto borrado",
                                    "Se ha borrado el departamento", "Aceptar",
                                    new OnSubmitSimpleListener() {
                                        @Override
                                        public void submit(Object result) {
                                            // no se recarga la lista hasta que se pulsa aceptar
                                            lista = departamentoRepositorio.recuperarTodos();
                                            adaptadorDeptos.setListDepartamentos(lista);
                                            adaptadorDeptos.notifyDataSetChanged();
                                        }
                                    });
                            info.show(getFragmentManager(), "deptoborrado");
                        }
                    }
                },
                new OnSubmitSimpleListener() {
                    @Override
                    public void submit(Object result) {
                        // negative listener
                        SimpleInfoOkBtnDialog info = new SimpleInfoOkBtnDialog();
                        info.setConfiguration("Depto borrado cancelado",
                                "Finalmente no se ha borrado el departamento", "Aceptar",
                                new OnSubmitSimpleListener() {
                                    @Override
                                    public void submit(Object result) {
                                    }
                                });
                        info.show(getFragmentManager(), "deptoborrado");
                    }
                });
        dialog.show(getFragmentManager(), "Etiqueta dialogo");
    }

}

