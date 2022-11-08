package es.rodrigo.learning.pmdm.ejemplodialogos.dialogos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import es.rodrigo.learning.pmdm.ejemplodialogos.R;

@SuppressLint("ValidFragment")
public class SimpleInfoDialog extends DialogFragment {
    private String title;
    private String message;

    @SuppressLint("ValidFragment")
    public SimpleInfoDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(title);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
