package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SubmitActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        TextView storeName = findViewById(R.id.storeName);
        TextView storeAddress = findViewById(R.id.storeAddress);
        TextView reviewCount = findViewById(R.id.reviewCountTextView);

        CheckBox ppeRadio = findViewById(R.id.ppeRadioBtn);
        CheckBox sanitizerRadio = findViewById(R.id.sanitizerRadioBtn);

        SeekBar cleanlinessSeekBar = findViewById(R.id.cleanlinessSeekBar);
        SeekBar socialDistanceSeekBar = findViewById(R.id.socialDistanceSeekBar);
        SeekBar safetySeekbar = findViewById(R.id.safetySeekBar);

        Button returnBtn = findViewById(R.id.returnBtn);
        Button submitBtn = findViewById(R.id.submitBtn);

        // Get current intent
        Intent intent = getIntent();
        ArrayList<List<String>> restoList = (ArrayList<List<String>>) intent.getSerializableExtra("STORE_LIST");
        int storeIndex = intent.getIntExtra("STORE_INDEX", -1);

        // Set restaurant name and address
        storeName.setText(restoList.get(storeIndex).get(0));
        storeAddress.setText(restoList.get(storeIndex).get(1));
        reviewCount.setText("Number of Reviews: " + restoList.get(storeIndex).get(3));

        // Set seek bars
        cleanlinessSeekBar.setMax(5);
        socialDistanceSeekBar.setMax(5);
        safetySeekbar.setMax(5);
        cleanlinessSeekBar.setProgress(3);
        socialDistanceSeekBar.setProgress(3);
        safetySeekbar.setProgress(3);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainPage.class);
                intent.putExtra("STORE_LIST", restoList);
                startActivity(intent);
            }
        });


        // Listener for Submit button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ppeValue;
                int sanitizerValue;
                // When review is submitted, get the values
                if(ppeRadio.isChecked()) {
                    ppeValue = 100;
                } else {
                    ppeValue = 0;
                }
                if(sanitizerRadio.isChecked()) {
                    sanitizerValue = 100;
                } else {
                    sanitizerValue = 0;
                }

                restoList.get(storeIndex).set(3, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(3)) + 1));
                restoList.get(storeIndex).set(5, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(5)) + ppeValue));
                restoList.get(storeIndex).set(6, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(6)) + sanitizerValue));
                restoList.get(storeIndex).set(7, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(7)) + cleanlinessSeekBar.getProgress()));
                restoList.get(storeIndex).set(8, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(8)) + socialDistanceSeekBar.getProgress()));
                restoList.get(storeIndex).set(9, String.valueOf(Integer.parseInt(restoList.get(storeIndex).get(9)) + safetySeekbar.getProgress()));

                // update number of reviews
                reviewCount.setText("Number of Reviews: " + restoList.get(storeIndex).get(3));

                // notifies returning to main page
                Toast.makeText(v.getContext(), "Review submitted! Returning to main page..", Toast.LENGTH_LONG).show();

                // returns to main page after 2 seconds
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent (v.getContext(), MainPage.class);
                        intent.putExtra("STORE_LIST", restoList);
                        startActivity(intent);
                    }
                }, 3000);
            }
        });
    }
}