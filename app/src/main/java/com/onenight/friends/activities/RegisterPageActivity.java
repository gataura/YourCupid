package com.onenight.friends.activities;

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

import com.onenight.friends.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPageActivity extends AppCompatActivity {

    private EditText voytiInEditText;
    private EditText parolEditText;
    private EditText etEmail;
    private DatePicker datePicker;
    private Button btnRegister;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_page);

        voytiInEditText = findViewById(R.id.login_reg_edit_text);
        parolEditText = findViewById(R.id.parol_reg_edit_text);
        etEmail = findViewById(R.id.type_info_email_et);
        btnRegister = findViewById(R.id.reg_knopka);
        datePicker = findViewById(R.id.vibor_dati_reg);

        setTypefaces();

        final Intent intent = new Intent(this, TipaMainActivity.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!voytiInEditText.getText().toString().equals("") || !parolEditText.getText().toString().equals("")
                        || !etEmail.getText().toString().equals("")){
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
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
        Toast.makeText(getApplicationContext(),
                strDate, Toast.LENGTH_SHORT).show();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        voytiInEditText.setTypeface(typeface);
        parolEditText.setTypeface(typeface);
        etEmail.setTypeface(typeface);
        btnRegister.setTypeface(typeface);
    }

}
