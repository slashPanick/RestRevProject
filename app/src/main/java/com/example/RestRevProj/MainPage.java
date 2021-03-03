// did example of ArrayList working with ListView
// todo: new activity for submitting *deviate from sb on result*
// todo: new style for buttons: float border 75% transparent

package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPage extends AppCompatActivity {
    ListView storeList;
    EditText searchEditText;
    ArrayList<String> stores = new ArrayList<>();           //start with empty arraylist
    ArrayAdapter<String> arrayAdapter;
    TextView selectedStoreName;
    TextView selectedStoreAddress;
    Button reviewBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        storeList = findViewById(R.id.storeList);
        searchEditText = findViewById(R.id.searchEditText);
        selectedStoreName = findViewById(R.id.selectedStoreName);
        selectedStoreAddress = findViewById(R.id.selectedStoreAddress);
        reviewBtn = findViewById(R.id.reviewBtn);

        findViewById(R.id.constraintLayout).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        String str = null;
        BufferedReader br = null;
        List<List<String>> restoList = new ArrayList<>();

        //noinspection TryWithIdenticalCatches
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

        for (int i = 0; i < restoList.size(); i++) {
            stores.add(restoList.get(i).get(0).toUpperCase());
        }

        // arrayAdapter is required for ListView to function. params are just context, the ListView, the item (text1) in the ListView,
        // and what is going in it (stores array). then the adapter is set as the one used by storeList ListView. changes the list item
        // into an object
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stores);
        storeList.setAdapter(arrayAdapter);

        // this is for the search, generally using editText is less superior way, but actual searchView is more complicated imo
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectedStoreAddress.setText(restoList.get(position).get(1));
                selectedStoreName.setText((String) storeList.getItemAtPosition(position));
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStoreName.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Must select a store", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(v.getContext(), ReadActivity.class);
                    startActivity(intent);
                }
            }
        });
    }}