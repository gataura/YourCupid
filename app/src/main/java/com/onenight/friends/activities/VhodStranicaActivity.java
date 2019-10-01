package com.onenight.friends.activities;

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

import com.onenight.friends.R;
import com.onenight.friends.utils.Utility;

public class VhodStranicaActivity extends AppCompatActivity {

    private EditText parolEdit;
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

        parolEdit = findViewById(R.id.parol_vhod_edit_text);
        etEmail = findViewById(R.id.pochta_vhod_edit_text);
        btnEnter = findViewById(R.id.voyti_vhod_knopka);
        btnRegistry = findViewById(R.id.net_acca_knopka);

        setTypefaces();

        Utility.checkPermission(VhodStranicaActivity.this);

        final Intent intent = new Intent(this, RezultatActivity.class);
        final Intent intentRegister = new Intent(this, RegisterPageActivity.class);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parolEdit.getText().toString().equals("") || !etEmail.getText().toString().equals("")){
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
        parolEdit.setTypeface(typeface);
        etEmail.setTypeface(typeface);
        btnEnter.setTypeface(typeface);
    }
}
