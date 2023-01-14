package com.dam.android.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.android.ProyectoApplication;
import com.dam.android.R;
import com.dam.android.modelos.Actividad;
import com.dam.android.modelos.Departamento;
import com.dam.android.util.Utilidades;

import java.util.List;

public class EditarActividadDialog extends DialogFragment {
    private OnSubmitSimpleListener okListener;
    private OnSubmitSimpleListener cancelListener;
    private Actividad actividad;
    private String message;
    private String positiveBtnLabel;
    private String negativeBtnLabel;

    private TextView tvIdActividad;
    private EditText etDialogLugarAct;
    private EditText etDialogTituloAct;
    private Spinner spDialogDeptoAct;

    private List<Departamento> deptosList;

    public void setConfiguration(Actividad actividad, String message,
                                 String positiveBtnLabel, String negativeBtnLabel,
                                 OnSubmitSimpleListener okListener,
                                 OnSubmitSimpleListener cancelListener) {
        this.okListener = okListener;
        this.cancelListener = cancelListener;
        this.actividad = actividad;
        this.message = message;
        this.positiveBtnLabel = positiveBtnLabel;
        this.negativeBtnLabel = negativeBtnLabel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // builder.setCancelable(false);
        // Usamos el Inflater para crear la vista a partir del XML Layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vistaInflada = inflater.inflate(R.layout.dialog_actividad_editar, null);
        builder.setView(vistaInflada);
        // Bindeamos e inicializamos los widgets que necesitemos usar
        TextView tvTitulo = (TextView) vistaInflada.findViewById(R.id.tvTitulo);
        tvTitulo.setText(message);

        tvIdActividad = vistaInflada.findViewById(R.id.tvIdActividad);
        etDialogLugarAct = vistaInflada.findViewById(R.id.etDialogLugarAct);
        etDialogTituloAct = vistaInflada.findViewById(R.id.etDialogTituloAct);
        spDialogDeptoAct = vistaInflada.findViewById(R.id.spDialogDeptoAct);

        deptosList = ProyectoApplication.getDepartamentoRepositorio().recuperarTodos();
        Utilidades.inicializarSpinner(spDialogDeptoAct, deptosList, "-- Seleccione un departamento --", getContext());

        if (actividad != null) {
            if (actividad.getId() != null) {
                tvIdActividad.setText(actividad.getId()+"");
            }
            etDialogLugarAct.setText(actividad.getLugar());
            etDialogTituloAct.setText(actividad.getTitulo());

            if (actividad.getDepartamento() != null) {
                spDialogDeptoAct.setSelection(deptosList.indexOf(actividad.getDepartamento()) + 1);
            } else {
                spDialogDeptoAct.setSelection(0);
            }
        }

        builder.setPositiveButton(positiveBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (okListener != null && !TextUtils.isEmpty(etDialogTituloAct.getText())){
                    actividad.setTitulo(etDialogTituloAct.getText().toString());
                    actividad.setLugar(etDialogLugarAct.getText().toString());
                    if (spDialogDeptoAct.getSelectedItemPosition() > 0) {
                        actividad.setDepartamento(deptosList.get(spDialogDeptoAct.getSelectedItemPosition() - 1));
                    }
                    okListener.submit(actividad);
                } else {
                    Toast.makeText(getContext(), "Debe escribir al menos un título, no se guarda la actividad", Toast.LENGTH_SHORT).show();
                    EditarActividadDialog.this.getDialog().cancel();
                }
            }
        });

        builder.setNegativeButton(negativeBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cancelListener != null) {
                    cancelListener.submit(null);
                } else {
                    EditarActividadDialog.this.getDialog().cancel();
                }
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
