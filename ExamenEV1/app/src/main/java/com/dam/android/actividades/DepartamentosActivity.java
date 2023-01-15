package com.dam.android.actividades;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.android.ProyectoApplication;
import com.dam.android.R;
import com.dam.android.adaptadores.DepartamentoAdapter;
import com.dam.android.dialogos.InputBoxDialog;
import com.dam.android.dialogos.OkCancelDialog;
import com.dam.android.dialogos.OnSubmitSimpleListener;
import com.dam.android.dialogos.SimpleInfoOkBtnDialog;
import com.dam.android.modelos.Departamento;
import com.dam.android.repositorios.DepartamentoRepositorio;
import com.dam.android.util.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class DepartamentosActivity extends AppCompatActivity {
    private DepartamentoRepositorio departamentoRepositorio;
    private Button btNuevoDepto;
    private EditText etNombre;
    private ListView lvDeptos;
    private List<Departamento> lista;
    private DepartamentoAdapter adaptadorDeptos;
    private View oldViewGround;
    private int indiceDepartamento = -1;

    private ProgressBar pbCargaDptos;

    // Inicio métodos necesarios para manejar NavigateUp y OptionsMenu.
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_especial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Se presionó Añadir", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(this, "Se presionó Buscar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit:
                Toast.makeText(this, "Se presionó Editar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, "Se presionó Eliminar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Se presionó Preferencias", Toast.LENGTH_SHORT).show();
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
        pbCargaDptos = (ProgressBar) findViewById(R.id.pbCargaDptos);

        // preparar y cargar listview de departamentos
        // añadimos la cabecera
        ViewGroup header = (ViewGroup) getLayoutInflater().
                inflate(R.layout.departamento_list_header,
                lvDeptos, false);
        if (lvDeptos.getHeaderViewsCount() == 0) {
            lvDeptos.addHeaderView(header);
        }
        // cargamos la lista de departamentos
        lista = new ArrayList<>();
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
                if (Utilidades.validarCampoObligatorio(etNombre, getString(R.string.campoobligatorio))) {
                    Departamento d = new Departamento(etNombre.getText().toString());
                    etNombre.setText("");
                    new ActualizarDepartamentoTaks(d).execute();
               }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CargarDepartamentosTask().execute();
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
                            new ActualizarDepartamentoTaks(d).execute();
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
                            d.setEliminado(true);
                            new ActualizarDepartamentoTaks(d).execute();
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



    private class CargarDepartamentosTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            lvDeptos.setVisibility(View.GONE);
            pbCargaDptos.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            lista = ProyectoApplication.getDepartamentoRepositorio().recuperarTodos();
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            lvDeptos.setVisibility(View.VISIBLE);
            pbCargaDptos.setVisibility(View.GONE);

            if (lista == null) {
                Toast.makeText(DepartamentosActivity.this, "Error al cargar los departamentos", Toast.LENGTH_SHORT).show();
                lista = new ArrayList<>();
            }

            adaptadorDeptos.setListDepartamentos(lista);
            adaptadorDeptos.notifyDataSetChanged();
            indiceDepartamento = -1;
        }
    }

    private class ActualizarDepartamentoTaks extends AsyncTask<Void, Void, Void> {
        private Departamento departamento;

        public ActualizarDepartamentoTaks(Departamento departamento) {
            this.departamento = departamento;
        }

        @Override
        protected void onPreExecute() {
            lvDeptos.setVisibility(View.GONE);
            pbCargaDptos.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            departamentoRepositorio.guardar(departamento);
            lista = ProyectoApplication.getDepartamentoRepositorio().recuperarTodos();
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            lvDeptos.setVisibility(View.VISIBLE);
            pbCargaDptos.setVisibility(View.GONE);

            if (lista == null) {
                Toast.makeText(DepartamentosActivity.this, "Error al actualizar el departamento", Toast.LENGTH_SHORT).show();
                lista = new ArrayList<>();
            } else {
                Toast.makeText(DepartamentosActivity.this, "Departamento actualizado con éxito", Toast.LENGTH_SHORT).show();
            }

            adaptadorDeptos.setListDepartamentos(lista);
            adaptadorDeptos.notifyDataSetChanged();
            indiceDepartamento = -1;
        }
    }

}

