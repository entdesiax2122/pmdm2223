package es.rodrigo.learning.pmdm.ejemplodialogos.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {
    public static boolean validarCampoObligatorio(EditText editText, String errorMsg) {
        boolean res = true;
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError(errorMsg);
            res = false;
        }
        return res;
    }

    public static void inicializarSpinner(Spinner spinner, List lista, String primerElemento, Context context){
        ArrayList<String> listaString = new ArrayList<>();
        if (primerElemento != null) {
            listaString.add(primerElemento);
        }
        if (lista != null && !lista.isEmpty())  {
            for (Object o : lista) {
                listaString.add(o.toString());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, listaString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
