package com.your.cupid.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.your.cupid.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPageActivity extends AppCompatActivity {

    private EditText voytiInEditTextsdfsdfdsf;
    private EditText parolsdfdfEditText;
    private EditText etEmaildfgdg;
    private DatePicker datesdfsdfPicker;
    private Button btnRegister;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_page);

        voytiInEditTextsdfsdfdsf = findViewById(R.id.login_reg_edit_text_sdfsd);
        parolsdfdfEditText = findViewById(R.id.parol_reg_edit_text_wef);
        etEmaildfgdg = findViewById(R.id.type_info_email_et_sdfs);
        btnRegister = findViewById(R.id.reg_knopka_wefw);
        datesdfsdfPicker = findViewById(R.id.vibor_sefsefsd_dati_reg);

        setTypefaces();

        final Intent intent = new Intent(this, TipaMainActivity.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!voytiInEditTextsdfsdfdsf.getText().toString().equals("") || !parolsdfdfEditText.getText().toString().equals("")
                        || !etEmaildfgdg.getText().toString().equals("")){
                    intent.putExtra("flag", "RegisterPageActivity");
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.fill_right_fields), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDate() {
        int day = datesdfsdfPicker.getDayOfMonth();
        int month = datesdfsdfPicker.getMonth() + 1;
        int year = datesdfsdfPicker.getYear();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        Toast.makeText(getApplicationContext(),
                strDate, Toast.LENGTH_SHORT).show();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        voytiInEditTextsdfsdfdsf.setTypeface(typeface);
        parolsdfdfEditText.setTypeface(typeface);
        etEmaildfgdg.setTypeface(typeface);
        btnRegister.setTypeface(typeface);
    }

}
