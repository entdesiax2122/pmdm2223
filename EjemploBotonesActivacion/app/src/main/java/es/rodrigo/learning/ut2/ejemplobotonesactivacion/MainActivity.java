package es.rodrigo.learning.ut2.ejemplobotonesactivacion;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CompoundButton.OnCheckedChangeListener mylistener =
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,
                                         boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Toast.makeText(MainActivity.this,
                            "El widget está activado!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // The toggle is disabled
                    Toast.makeText(MainActivity.this,
                            "El widget está desactivado!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        final ToggleButton toggle = (ToggleButton)
                findViewById(R.id.toggleButton);
        final Switch switchl = (Switch) findViewById(R.id.switchl);
        toggle.setOnCheckedChangeListener(mylistener);
        switchl.setOnCheckedChangeListener(mylistener);


    }
}