package es.rodrigo.learning.ut2.prac1;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText etA;
    private EditText etB;
    private TextView etRes;
    private Button btSum;
    private Button btRes;
    private Button btMul;
    private Button btDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etA = (EditText) findViewById(R.id.etA);
        etB = (EditText) findViewById(R.id.etB);
        etRes = (TextView) findViewById(R.id.etRes);
        btSum = (Button) findViewById(R.id.btSum);
        btRes = (Button) findViewById(R.id.btRes);
        btMul = (Button) findViewById(R.id.btMul);
        btDiv = (Button) findViewById(R.id.btDiv);

        btSum.setOnClickListener(view -> operar("+"));
        btRes.setOnClickListener(view -> operar("-"));
        btMul.setOnClickListener(view -> operar("*"));
        btDiv.setOnClickListener(view -> operar("/"));
    }

    private void operar(String op) {
        if (TextUtils.isEmpty(etA.getText())) {
            etA.setText("0");
        }
        if (TextUtils.isEmpty(etB.getText())) {
            etB.setText("0");
        }
        double valA = Double.parseDouble(etA.getText().toString());
        double valB = Double.parseDouble(etB.getText().toString());
        double res = 0;
        switch (op) {
            case "+":
                res = valA + valB;
                break;
            case "-":
                res = valA - valB;
                break;
            case "*":
                res = valA * valB;
                break;
            case "/":
                res = valA / valB;
                break;
        }
        etRes.setText(String.format("%s", res));
    }
}