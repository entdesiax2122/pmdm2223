package es.rodrigo.learning.ut1.holamundoiax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String msg = "Ciclo de vida - onCreate";
        Log.d(TAG, msg);
        setContentView(R.layout.activity_main);
    }
}