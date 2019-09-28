package com.datingapp.reallove.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.datingapp.reallove.R;
import com.datingapp.reallove.utils.Utility;

public class VhodStranicaActivity extends AppCompatActivity {

    private EditText etPass;
    private EditText etEmail;
    private Button btnEnter;
    private Button btnRegistry;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vhod_stranica);

        etPass = findViewById(R.id.sign_in_password_et);
        etEmail = findViewById(R.id.sign_in_email_et);
        btnEnter = findViewById(R.id.sign_in_button);
        btnRegistry = findViewById(R.id.sign_in_dont_have_acc);

        setTypefaces();

        Utility.checkPermission(VhodStranicaActivity.this);

        final Intent intent = new Intent(this, ResultActivity.class);
        final Intent intentRegister = new Intent(this, RegStranicaActivity.class);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etPass.getText().toString().equals("") || !etEmail.getText().toString().equals("")){
                    intent.putExtra("flag", "VhodStranicaActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            R.string.fill_right_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRegister);
            }
        });
    }
    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        etPass.setTypeface(typeface);
        etEmail.setTypeface(typeface);
        btnEnter.setTypeface(typeface);
    }
}
