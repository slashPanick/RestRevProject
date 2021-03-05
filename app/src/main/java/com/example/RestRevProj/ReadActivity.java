package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    Button returnBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView storeName = findViewById(R.id.storeTextView);
        TextView storeAddress = findViewById(R.id.addressTextView);
        TextView reviewCount = findViewById(R.id.reviewCountTextView);
        TextView ppePercent = findViewById(R.id.ppeRadioBtn);
        TextView sanitizerPercent = findViewById(R.id.sanitizerRadioBtn);
        ProgressBar progressBarCleanliness = findViewById(R.id.progressBar);
        ProgressBar progressBarSD = findViewById(R.id.progressBar2);
        ProgressBar progressBarSafety = findViewById(R.id.progressBar3);

        // Get current intent
        Intent intent = getIntent();
        ArrayList<List<String>> restoList = (ArrayList<List<String>>) intent.getSerializableExtra("STORE_LIST");
        int restaurantPosition = intent.getIntExtra("SELECTED_STORE_POSITION", -1);

        // Set restaurant name and address
        storeName.setText(restoList.get(restaurantPosition).get(0));
        storeAddress.setText(restoList.get(restaurantPosition).get(1));
        reviewCount.setText("Number of Reviews: " + restoList.get(restaurantPosition).get(3));
        ppePercent.setText(Math.round(Double.parseDouble(restoList.get(restaurantPosition).get(5))/Double.parseDouble(restoList.get(restaurantPosition).get(3))) + "%");
        sanitizerPercent.setText(Math.round(Double.parseDouble(restoList.get(restaurantPosition).get(6))/Double.parseDouble(restoList.get(restaurantPosition).get(3))) + "%");
        // Set progress bar maximums
        progressBarCleanliness.setMax(5);
        progressBarSD.setMax(5);
        progressBarSafety.setMax(5);
        // Set progress bars using given values from file (will round to appropriate int from double)
        progressBarCleanliness.setProgress((int) Math.round(Double.parseDouble(restoList.get(restaurantPosition).get(7))/Double.parseDouble(restoList.get(restaurantPosition).get(3))));
        progressBarSD.setProgress((int) Math.round(Double.parseDouble(restoList.get(restaurantPosition).get(8))/Double.parseDouble(restoList.get(restaurantPosition).get(3))));
        progressBarSafety.setProgress((int) Math.round(Double.parseDouble(restoList.get(restaurantPosition).get(9))/Double.parseDouble(restoList.get(restaurantPosition).get(3))));



        returnBtn = findViewById(R.id.returnBtn);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainPage.class);
                intent.putExtra("STORE_LIST", restoList);
                startActivity(intent);
            }
        });

    }}