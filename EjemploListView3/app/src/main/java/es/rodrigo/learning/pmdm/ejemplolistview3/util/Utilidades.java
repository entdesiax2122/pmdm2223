package es.rodrigo.learning.pmdm.ejemplolistview3.util;

import android.text.TextUtils;
import android.widget.EditText;

public class Utilidades {
    public static boolean validarCampoObligatorio(EditText editText, String errorMsg) {
        boolean res = true;
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError(errorMsg);
            res = false;
        }
        return res;
    }
}
