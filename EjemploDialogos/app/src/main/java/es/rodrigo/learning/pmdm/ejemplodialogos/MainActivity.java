package es.rodrigo.learning.pmdm.ejemplodialogos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.rodrigo.learning.pmdm.ejemplodialogos.dialogs.SimpleInfoDialog;

public class MainActivity extends Activity {
    private Button btnSimpleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bindear componentes
        btnSimpleInfo = findViewById(R.id.btnSimpleInfo);
        btnSimpleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleInfoDialog sid = new SimpleInfoDialog();
                sid.show(getFragmentManager(),"Mi diálogo");
            }
        });
    }


}