package es.rodrigo.learning.ut2.ejemplousocontext;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context contexto = getApplicationContext();
        Context contexto2 = getBaseContext();
//        Context contexto3 = getActivity(); //Sólo desde dentro de un Fragment

        Context contexto4 = this;

        final Button boton = (Button) findViewById(R.id.bt_obten_context);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context contexto5 = MainActivity.this;
            }
        });
    }
}

