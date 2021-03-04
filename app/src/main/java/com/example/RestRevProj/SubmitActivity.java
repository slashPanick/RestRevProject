package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {


    TextView storeName;
    TextView storeAddress;
    TextView reviewCount;

    RadioButton ppeRadio;
    RadioButton sanitizerRadio;

    SeekBar cleanlinessSeekBar;
    SeekBar socialDistanceSeekBar;
    SeekBar safetySeekbar;

    Button returnBtn;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        reviewCount = findViewById(R.id.reviewCountTextView);

        ppeRadio = findViewById(R.id.ppeRadioBtn);
        sanitizerRadio = findViewById(R.id.sanitizerRadioBtn);

        cleanlinessSeekBar = findViewById(R.id.cleanlinessSeekBar);
        socialDistanceSeekBar = findViewById(R.id.socialDistanceSeekBar);
        safetySeekbar = findViewById(R.id.safetySeekBar);

        returnBtn = findViewById(R.id.returnBtn);
        submitBtn = findViewById(R.id.submitBtn);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainPage.class);
                startActivity(intent);
            }
        });




        // Listener for Submit button (should write reviews to csv?)
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}