package com.dam.android.actividades;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.android.ProyectoApplication;
import com.dam.android.R;
import com.dam.android.adaptadores.ActividadAdapter;
import com.dam.android.dialogos.EditarActividadDialog;
import com.dam.android.dialogos.InputBoxDialog;
import com.dam.android.dialogos.OkCancelDialog;
import com.dam.android.dialogos.OnSubmitSimpleListener;
import com.dam.android.modelos.Actividad;
import com.dam.android.modelos.Departamento;
import com.dam.android.repositorios.ActividadRepositorio;

import java.util.List;


public class ActividadesActivity extends AppCompatActivity {
    private ActividadRepositorio actividadRepositorio;
    private ListView lvActividades;
    private List<Actividad> lista;
    private ActividadAdapter adaptadorActividades;
    private View oldViewGround;
    private int indiceActividad = -1;

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
                crearActividad();
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
        setContentView(R.layout.activity_actividades);

        // Ini ActionBar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iax_logo);
        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        // Fin ActionBar

        actividadRepositorio = ProyectoApplication.getActividadRepositorio();
        lvActividades = (ListView) findViewById(R.id.lvActividades);

        // preparar y cargar listview de Actividads
        // añadimos la cabecera
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.actividad_list_header, lvActividades, false);
        if (lvActividades.getHeaderViewsCount() == 0) {
            lvActividades.addHeaderView(header);
        }
        // cargamos la lista de Actividads
        lista = actividadRepositorio.recuperarTodos();
        // creamos el Adaptador personalizado y se lo añadimos al ListView
        adaptadorActividades = new ActividadAdapter(getBaseContext(), lista);

        lvActividades.setAdapter(adaptadorActividades);
        // programamos el AdapterView.OnItemClickListener
        lvActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    indiceActividad = position - 1;
                    if (oldViewGround != null && view != oldViewGround) {
                        oldViewGround.setBackgroundResource(R.drawable.recuadro_list_item);
                    }
                    view.setBackgroundColor(getColor(R.color.amber));
                    oldViewGround = view;
                }
            }
        });
    }

    public void crearActividad() {
            Actividad u = new Actividad();
            EditarActividadDialog dialogo = new EditarActividadDialog();
            dialogo.setConfiguration(u, "Introduzca los datos de la nueva actividad",
                    "Aceptar", "Cancelar",
                    new OnSubmitSimpleListener() {
                        @Override
                        public void submit(Object result) {
                            Actividad actividad = (Actividad) result;
                            actividadRepositorio.guardar(actividad);
                            lista = actividadRepositorio.recuperarTodos();
                            adaptadorActividades.setListActividades(lista);
                            adaptadorActividades.notifyDataSetChanged();
                            Toast.makeText(ActividadesActivity.this, "Actividad creada ...", Toast.LENGTH_SHORT).show();
                        }
                    },
                    null
            );
            dialogo.show(getFragmentManager(), "Dialogo crear actividad");
    }

}

