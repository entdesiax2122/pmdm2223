package es.rodrigo.learning.pmdm.ejemplodialogos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.rodrigo.learning.pmdm.ejemplodialogos.dialogs.SimpleInfoDialog;
import es.rodrigo.learning.pmdm.ejemplodialogos.dialogs.SimpleInfoOkBtnDialog;

public class MainActivity extends Activity {
    private Button btnSimpleInfo;
    private Button btnSimpleInfoOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bindear componentes
        btnSimpleInfo = findViewById(R.id.btnSimpleInfo);
        btnSimpleInfoOkBtn = findViewById(R.id.btnSimpleInfoOkBtn);

        btnSimpleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoDialog sid = new SimpleInfoDialog();
                sid.show(getFragmentManager(),"Mi diálogo simple");
            }
        });

        btnSimpleInfoOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoOkBtnDialog siobd = new SimpleInfoOkBtnDialog();
                siobd.show(getFragmentManager(), "Mi dialogo simple con botón aceptar");
            }
        });
    }


}