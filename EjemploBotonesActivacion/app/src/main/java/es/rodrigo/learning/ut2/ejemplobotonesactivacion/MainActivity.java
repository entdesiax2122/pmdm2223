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

        final ToggleButton toggle = (ToggleButton)
                findViewById(R.id.toggleButton);
        final Switch switchl = (Switch) findViewById(R.id.switchl);

        final CompoundButton.OnCheckedChangeListener mylistener =
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,
                                         boolean isChecked) {
                if (compoundButton.getId()==R.id.toggleButton) {
                   // el evento es del toggle

                } else if (compoundButton.getId()==R.id.switchl) {
                    // el evento es del switch

//                    switchl.setText();
                }
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
        toggle.setOnCheckedChangeListener(mylistener);
        switchl.setOnCheckedChangeListener(mylistener);


    }
}