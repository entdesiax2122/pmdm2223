package es.rodrigo.learning.ejemplo1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.rodrigo.learning.ejemplo1.R;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  " + getString(R.string.app_name));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5e9c00")));

        mAuth = FirebaseAuth.getInstance();
        mTvUser = (TextView) findViewById(R.id.tvUser);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mTvUser.setText(currentUser.getEmail());
        }

        Button btLogout = (Button) findViewById(R.id.btLogout);
        btLogout.setOnClickListener(view -> {
            mAuth.signOut();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}