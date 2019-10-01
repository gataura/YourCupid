package com.onenight.friends.activities;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onenight.friends.R;

public class WhatAreYouIsheshForActivity extends AppCompatActivity {

    private RadioButton muzhikOtvet;
    private RadioButton babaOtvet;
    private Button knopkaContinue;
    private TextView text;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_what_are_you_ishesh_for);

        muzhikOtvet = findViewById(R.id.muzhika_ishu_radio);
        babaOtvet = findViewById(R.id.babu_ishu_radio);
        knopkaContinue = findViewById(R.id.sleduyushaya_knopka);
        text = findViewById(R.id.text_che_ishesh);
        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, RezultatActivity.class);

        knopkaContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (muzhikOtvet.isChecked() || babaOtvet.isChecked()) {
                    intent.putExtra("flag", checkFlag);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.who_are_you_looking_for, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTypefaces() {

        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        text.setTypeface(typeface);
        knopkaContinue.setTypeface(typeface);
        muzhikOtvet.setTypeface(typeface);
        babaOtvet.setTypeface(typeface);
    }
}
