package es.rodrigo.learning.pmdm.ejemplodialogos.dialogos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import es.rodrigo.learning.pmdm.ejemplodialogos.R;

public class SimpleInfoOkBtnDialog extends DialogFragment {
    private String title;
    private String message;
    private String positiveBtnLabel;
    private OnSubmitSimpleListener okListener;

    @SuppressLint("ValidFragment")
    public SimpleInfoOkBtnDialog(String title, String message, String positiveBtnLabel, OnSubmitSimpleListener okListener) {
        this.title = title;
        this.message = message;
        this.positiveBtnLabel = positiveBtnLabel;
        this.okListener = okListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positiveBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (okListener != null) {
                    okListener.submit(null);
                }
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
