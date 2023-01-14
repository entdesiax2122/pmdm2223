package com.dam.android.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OkCancelDialog extends DialogFragment {
    private OnSubmitSimpleListener okListener;
    private OnSubmitSimpleListener cancelListener;
    private String title;
    private String message;
    private String positiveBtnLabel;
    private String negativeBtnLabel;

    public void setConfiguration(String title, String message,
                          String positiveBtnLabel, String negativeBtnLabel,
                          OnSubmitSimpleListener okListener,
                          OnSubmitSimpleListener cancelListener) {
        this.okListener = okListener;
        this.cancelListener = cancelListener;
        this.title = title;
        this.message = message;
        this.positiveBtnLabel = positiveBtnLabel;
        this.negativeBtnLabel = negativeBtnLabel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(title);
        // builder.setCancelable(false);
        builder.setPositiveButton(positiveBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (okListener != null ){
                    okListener.submit(null);
                }
            }
        });
        builder.setNegativeButton(negativeBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cancelListener != null) {
                    cancelListener.submit(null);
                }
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
