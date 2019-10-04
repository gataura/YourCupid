package com.your.cupid.activities;

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

import com.your.cupid.R;
import com.your.cupid.utils.Utility;

public class VhodStranicaActivity extends AppCompatActivity {

    private EditText parolsdfsdwEdit;
    private EditText etsdfsdfEmail;
    private Button btsdfsdnEnter;
    private Button btnsegfwRegistry;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vhod_stranica);

        parolsdfsdwEdit = findViewById(R.id.parol_vhod_edit_text_sdfwdf);
        etsdfsdfEmail = findViewById(R.id.pochta_vhod_edit_text_wefwef);
        btsdfsdnEnter = findViewById(R.id.voyti_vhod_knopka_sdfsdf);
        btnsegfwRegistry = findViewById(R.id.net_acca_knopka_sfdqq);

        setTypefaces();

        Utility.checkPermission(VhodStranicaActivity.this);

        final Intent intent = new Intent(this, RezultatActivity.class);
        final Intent intentRegister = new Intent(this, RegisterPageActivity.class);
        btsdfsdnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parolsdfsdwEdit.getText().toString().equals("") || !etsdfsdfEmail.getText().toString().equals("")){
                    intent.putExtra("flag", "VhodStranicaActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            R.string.fill_right_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsegfwRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRegister);
            }
        });
    }
    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        parolsdfsdwEdit.setTypeface(typeface);
        etsdfsdfEmail.setTypeface(typeface);
        btsdfsdnEnter.setTypeface(typeface);
    }
}
