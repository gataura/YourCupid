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

public class CheIsheshActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_che_ishesh);

        maleAnswer = findViewById(R.id.male_answer_look_for);
        femaleAnswer = findViewById(R.id.female_answer_look_for);
        knopkaContinue = findViewById(R.id.button_next_looking_for);
        text = findViewById(R.id.text_what_r_u_look);
        setTypefaces();

        final String checkFlag = getIntent().getStringExtra("flag");

        final Intent intent = new Intent(this, ResultActivity.class);

        knopkaContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleAnswer.isChecked() || femaleAnswer.isChecked()) {
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
        maleAnswer.setTypeface(typeface);
        femaleAnswer.setTypeface(typeface);
    }
}
