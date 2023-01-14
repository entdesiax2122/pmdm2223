package com.dam.android.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class SimpleInfoDialog extends DialogFragment {
    private String title;
    private String message;

    public void setConfiguration(String title, String message) {
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
