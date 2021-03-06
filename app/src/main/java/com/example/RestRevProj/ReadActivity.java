package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
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
        Button returnBtn = findViewById(R.id.returnBtn);
        Button submitBtn = findViewById(R.id.submitBtn);


        // Get current intent
        Intent intent = getIntent();
        ArrayList<List<String>> restoList = (ArrayList<List<String>>) intent.getSerializableExtra("STORE_LIST");
        int restoAddress = intent.getIntExtra("TEST_INDEX", -1);


        // Set restaurant name and address
        storeName.setText(restoList.get(restoAddress).get(0));
        storeAddress.setText(restoList.get(restoAddress).get(1));
        reviewCount.setText("Number of Reviews: " + restoList.get(restoAddress).get(3));
        ppePercent.setText(Math.round(Double.parseDouble(restoList.get(restoAddress).get(5))/Double.parseDouble(restoList.get(restoAddress).get(3))) + "%");
        sanitizerPercent.setText(Math.round(Double.parseDouble(restoList.get(restoAddress).get(6))/Double.parseDouble(restoList.get(restoAddress).get(3))) + "%");
        // Set progress bar maximums
        progressBarCleanliness.setMax(5);
        progressBarSD.setMax(5);
        progressBarSafety.setMax(5);
        // Set progress bars using given values from file (will round to appropriate int from double)
        progressBarCleanliness.setProgress((int) Math.round(Double.parseDouble(restoList.get(restoAddress).get(7))/Double.parseDouble(restoList.get(restoAddress).get(3))));
        progressBarSD.setProgress((int) Math.round(Double.parseDouble(restoList.get(restoAddress).get(8))/Double.parseDouble(restoList.get(restoAddress).get(3))));
        progressBarSafety.setProgress((int) Math.round(Double.parseDouble(restoList.get(restoAddress).get(9))/Double.parseDouble(restoList.get(restoAddress).get(3))));


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainPage.class);
                intent.putExtra("STORE_LIST", restoList);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SubmitActivity.class);
                intent.putExtra("SELECTED_STORE_POSITION", restoAddress);
                intent.putExtra("STORE_LIST", restoList);
                startActivity(intent);
            }
        });

    }}