package es.rodrigo.learning.ut1.holamundoiax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private String pilaLogs = "";
    private EditText etLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String msg = "Ciclo de vida - onCreate";
        Log.d(TAG, msg);

        setContentView(R.layout.activity_main);
        etLogs = (EditText) findViewById(R.id.etLogs);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String msg = "Ciclo de vida - onRestart";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String msg = "Ciclo de vida - onStart";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String msg = "Ciclo de vida - onResume";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String msg = "Ciclo de vida - onPause";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String msg = "Ciclo de vida - onStop";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String msg = "Ciclo de vida - onDestroy";
        Log.d(TAG, msg);
        actualizaEtLogs(msg);
    }

    private void actualizaEtLogs(String msg) {
        pilaLogs += msg + "\n";
        etLogs.setText(pilaLogs);
    }

}