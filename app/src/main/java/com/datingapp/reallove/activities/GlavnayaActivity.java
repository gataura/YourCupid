package com.datingapp.reallove.activities;

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

import com.datingapp.reallove.R;

public class GlavnayaActivity extends AppCompatActivity {

    private RadioButton maleAnswer;
    private RadioButton femaleAnswer;
    private Button knopkaContinue;
    private TextView text;
    private static final String FONT_PATH = "roboto_slab_regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_glavnaya);

        maleAnswer = findViewById(R.id.male_chose_gender);
        femaleAnswer = findViewById(R.id.female_chose_gender);
        knopkaContinue = findViewById(R.id.select_gender_next_button);
        text = findViewById(R.id.text_select_your_gender);

        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, CheIsheshActivity.class);
        knopkaContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleAnswer.isChecked() || femaleAnswer.isChecked()) {
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
        knopkaContinue.setTypeface(typeface);
        maleAnswer.setTypeface(typeface);
        femaleAnswer.setTypeface(typeface);
    }

}
