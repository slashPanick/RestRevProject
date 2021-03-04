package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubmitActivity extends AppCompatActivity {


    TextView storeName;
    TextView storeAddress;
    TextView reviewCount;

    CheckBox ppeRadio;
    CheckBox sanitizerRadio;

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

        // Get current intent
        Intent intent = getIntent();
        ArrayList<List<String>> restoList = (ArrayList<List<String>>) intent.getSerializableExtra("STORE_LIST");
        int restaurantPosition = intent.getIntExtra("SELECTED_STORE_POSITION", -1);

        // Set restaurant name and address
        storeName.setText(restoList.get(restaurantPosition).get(0));
        storeAddress.setText(restoList.get(restaurantPosition).get(1));
        reviewCount.setText("Number of Reviews: " + restoList.get(restaurantPosition).get(3));

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


        // Listener for Submit button (should write reviews to csv?)
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

                restoList.get(restaurantPosition).set(3, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(3)) + 1));
                restoList.get(restaurantPosition).set(5, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(5)) + ppeValue));
                restoList.get(restaurantPosition).set(6, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(6)) + sanitizerValue));
                restoList.get(restaurantPosition).set(7, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(7)) + cleanlinessSeekBar.getProgress()));
                restoList.get(restaurantPosition).set(8, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(8)) + socialDistanceSeekBar.getProgress()));
                restoList.get(restaurantPosition).set(9, String.valueOf(Integer.parseInt(restoList.get(restaurantPosition).get(9)) + safetySeekbar.getProgress()));

                // update number of reviews
                reviewCount.setText("Number of Reviews: " + restoList.get(restaurantPosition).get(3));

            }
        });
    }
}