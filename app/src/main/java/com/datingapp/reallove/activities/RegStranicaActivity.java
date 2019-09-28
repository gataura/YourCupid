package com.datingapp.reallove.activities;

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

import com.datingapp.reallove.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegStranicaActivity extends AppCompatActivity {

    private EditText editTextVoyti;
    private EditText etPass;
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
        setContentView(R.layout.activity_reg_stranica);

        editTextVoyti = findViewById(R.id.type_info_login_et);
        etPass = findViewById(R.id.type_info_password_et);
        etEmail = findViewById(R.id.type_info_email_et);
        btnRegister = findViewById(R.id.type_info_register_button);
        datePicker = findViewById(R.id.type_info_date_picker);

        setTypefaces();

        final Intent intent = new Intent(this, GlavnayaActivity.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextVoyti.getText().toString().equals("") || !etPass.getText().toString().equals("")
                        || !etEmail.getText().toString().equals("")){
                    intent.putExtra("flag", "RegStranicaActivity");
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
        editTextVoyti.setTypeface(typeface);
        etPass.setTypeface(typeface);
        etEmail.setTypeface(typeface);
        btnRegister.setTypeface(typeface);
    }

}
