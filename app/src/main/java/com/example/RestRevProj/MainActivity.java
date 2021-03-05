// bootup page, just leads into the mainPage activity

package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);

        String str = null;
        BufferedReader br = null;
        ArrayList<List<String>> restoList = new ArrayList<>();

        // Get restaurant info from .csv
        try {
            InputStream is = getResources().openRawResource(R.raw.restaurants);
            br = new BufferedReader(new InputStreamReader(is));

            while ((str = br.readLine()) != null) {
                String[] newResto = str.split(",");
                restoList.add(Arrays.asList(newResto));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainPage.class);
                intent.putExtra("STORE_LIST", restoList);
                startActivity(intent);
            }
        });
    }}