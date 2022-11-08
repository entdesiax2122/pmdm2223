package es.rodrigo.learning.pmdm.ejemplodialogos.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import es.rodrigo.learning.pmdm.ejemplodialogos.R;

public class SimpleInfoDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.simpleinfomsg)
                .setTitle(R.string.simpleinfotitle);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
