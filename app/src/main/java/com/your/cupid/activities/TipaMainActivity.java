package com.your.cupid.activities;

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

import com.your.cupid.R;

public class TipaMainActivity extends AppCompatActivity {

    private RadioButton muzhikOtvetsdfsdf;
    private RadioButton babadfsdfOtvet;
    private Button knopkasdfsdfContinue;
    private TextView text;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tipa_main);

        muzhikOtvetsdfsdf = findViewById(R.id.muzhik_male_sdfwdf_radio);
        babadfsdfOtvet = findViewById(R.id.baba_female_wefd_radio);
        knopkasdfsdfContinue = findViewById(R.id.ukazhi_pol_sdfsdf_dalshe_knopka);
        text = findViewById(R.id.ukazhi_pol_text);

        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, WhatAreYouIsheshForActivity.class);
        knopkasdfsdfContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (muzhikOtvetsdfsdf.isChecked() || babadfsdfOtvet.isChecked()) {
                    intent.putExtra("flag", checkFlag);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.choose_your_gender, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTypefaces() {

        Typeface typeface = Typeface.createFromAsset(getAssets(), FONT_PATH);
        text.setTypeface(typeface);
        knopkasdfsdfContinue.setTypeface(typeface);
        muzhikOtvetsdfsdf.setTypeface(typeface);
        babadfsdfOtvet.setTypeface(typeface);
    }

}
