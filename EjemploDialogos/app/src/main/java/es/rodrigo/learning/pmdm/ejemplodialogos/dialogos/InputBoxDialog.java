package es.rodrigo.learning.pmdm.ejemplodialogos.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import es.rodrigo.learning.pmdm.ejemplodialogos.R;

public class InputBoxDialog extends DialogFragment {
    private OnSubmitSimpleListener okListener;
    private OnSubmitSimpleListener cancelListener;
    private String initialValue;
    private String message;
    private String positiveBtnLabel;
    private String negativeBtnLabel;

    public void setConfiguration(String initialValue, String message,
                          String positiveBtnLabel, String negativeBtnLabel,
                          OnSubmitSimpleListener okListener,
                          OnSubmitSimpleListener cancelListener) {
        this.okListener = okListener;
        this.cancelListener = cancelListener;
        this.initialValue = initialValue;
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
        View vistaInflada = inflater.inflate(R.layout.dialog_input_box, null);
        builder.setView(vistaInflada);
        // Bindeamos e inicializamos los widgets que necesitemos usar
        TextView tvInputBox = (TextView) vistaInflada.findViewById(R.id.tvInputBox);
        tvInputBox.setText(message);
        EditText userInput = (EditText) vistaInflada.findViewById(R.id.etInputBox);
        if (initialValue != null) {
            userInput.setText(initialValue);
        }

        builder.setPositiveButton(positiveBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (okListener != null ){
                    okListener.submit(userInput.getText().toString());
                }
            }
        });
        builder.setNegativeButton(negativeBtnLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cancelListener != null) {
                    cancelListener.submit(null);
                } else {
                    InputBoxDialog.this.getDialog().cancel();
                }
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
