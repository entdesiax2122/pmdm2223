package es.rodrigo.learning.pmdm.ejemplodialogos.actividades;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

import es.rodrigo.learning.pmdm.ejemplodialogos.ProyectoApplication;
import es.rodrigo.learning.pmdm.ejemplodialogos.R;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.SimpleInfoDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogos.SimpleInfoOkBtnDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.modelos.Departamento;
import es.rodrigo.learning.pmdm.ejemplodialogos.util.Utilidades;

public class DialogosActivity extends AppCompatActivity {
    private Button btnSimpleInfo;
    private Button btnSimpleInfoOkBtn;
    private Button btnOkCancelDialog;
    private Spinner spDeptos;
    private TextView tvInfoDepto;
    private Button btSelecDepto;
    private List<Departamento> listDeptos;

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
        setContentView(R.layout.activity_dialogos);

        // Ini ActionBar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iax_logo);
        getSupportActionBar().setTitle(getString(R.string.iax_title));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        // Fin ActionBar

        // bindear componentes
        btnSimpleInfo = findViewById(R.id.btnSimpleInfo);
        btnSimpleInfoOkBtn = findViewById(R.id.btnSimpleInfoOkBtn);
        btnOkCancelDialog = findViewById(R.id.btnOkCancelDialog);
        tvInfoDepto = findViewById(R.id.tvInfoDepto);
        spDeptos = findViewById(R.id.spDeptos);
        btSelecDepto = findViewById(R.id.btSelecDepto);

        btnSimpleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoDialog sid = new SimpleInfoDialog();
                sid.setConfiguration("Título ejemplo","Mensaje de ejemplo que se muestra dentro del diálogo");
                sid.show(getFragmentManager(),"Mi diálogo simple");
            }
        });

        btnSimpleInfoOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoOkBtnDialog siobd = new SimpleInfoOkBtnDialog();
                siobd.setConfiguration("Título", "Mensaje", "Ok", null);
                siobd.show(getFragmentManager(), "Mi dialogo simple con botón aceptar");
            }
        });

        btnOkCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos el Intent
                Intent intent = new Intent(DialogosActivity.this, DepartamentosActivity.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        btSelecDepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int indiceDepto = spDeptos.getSelectedItemPosition();
                if (indiceDepto > 0) {
                    Departamento depSeleccionado = listDeptos.get(indiceDepto - 1);
                    tvInfoDepto.setText(depSeleccionado.getNombre());

                    //Creamos el Intent
                    Intent intent = new Intent(DialogosActivity.this, DepartamentosActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("deptoId", depSeleccionado.getId());
                    intent.putExtras(bundle);
                    //Iniciamos la nueva actividad
                    startActivity(intent);

                }
            }
        });

        // inicializar spinner
        listDeptos = ProyectoApplication.getDepartamentoRepositorio().recuperarTodos();
        Utilidades.inicializarSpinner(spDeptos, listDeptos, "-- Elige un departamento --", getBaseContext());
    }

    @Override
    protected void onResume() {
        // inicializar spinner
        super.onResume();
        listDeptos = ProyectoApplication.getDepartamentoRepositorio().recuperarTodos();
        Utilidades.inicializarSpinner(spDeptos, listDeptos, "-- Elige un departamento --", getBaseContext());
    }
}