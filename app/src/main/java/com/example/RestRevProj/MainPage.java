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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class MainPage extends AppCompatActivity {
    public static int storePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        ListView storeList  = findViewById(R.id.storeList);
        EditText searchEditText = findViewById(R.id.searchEditText);
        TextView selectedStoreName = findViewById(R.id.selectedStoreName);
        TextView selectedStoreAddress = findViewById(R.id.selectedStoreAddress);
        Button reviewBtn = findViewById(R.id.reviewBtn);
        Button submitBtn = findViewById(R.id.submitBtn);
        ArrayList<String> stores = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();

        // closes keyboard on touching outside keyboard
        findViewById(R.id.constraintLayout).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

        // brings in restoList from mainactivity
        Intent intent = getIntent();
        ArrayList<List<String>> restoList = (ArrayList<List<String>>) intent.getSerializableExtra("STORE_LIST");


        // splits restolist into 2 lists for name & address
        for (int i = 0; i < restoList.size(); i++) {
            stores.add(restoList.get(i).get(0).toUpperCase());
            addresses.add(restoList.get(i).get(1).toUpperCase());
        }


        // new adapter for 2 line items
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
        for (int i=0;i < restoList.size(); i++) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("name", stores.get(i));
            hashMap.put("address", addresses.get(i));
            arrayList.add(hashMap);
        }
        String[] from = {"name","address"};
        int[] to = {R.id.textView1, R.id.textView2};
        SimpleAdapter newAdapter = new SimpleAdapter(this, arrayList, R.layout.listview_item, from, to);
        storeList.setAdapter(newAdapter);


        // this is for the search, generally using editText is less superior way, but actual searchView is more complicated imo
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storeList.setFilterText(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                newAdapter.getFilter().filter(s.toString().trim());
                if (s.length() == 0) {
                    storeList.clearTextFilter();
                }
            }
        });


        // listener for selecting store from list
        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                storePosition = position;
                HashMap<String,String> map = (HashMap<String, String>) storeList.getItemAtPosition(position);
                String name = map.get("name");
                String address = map.get("address");
                selectedStoreAddress.setText(address);
                selectedStoreName.setText(name);
            }
        });


        // listener for moving to readactivity
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStoreName.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Must select a store", Toast.LENGTH_LONG).show();
                } else {
                    String currentAddress = (String) selectedStoreAddress.getText();
                    int storeIndex = addresses.indexOf(currentAddress);

                    Intent intent = new Intent(MainPage.this, ReadActivity.class);
                    intent.putExtra("STORE_INDEX", storeIndex);
                    intent.putExtra("STORE_LIST", restoList);
                    startActivity(intent);
                }
            }
        });


        // listener for moving to submitactivity
        submitBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStoreName.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Must select a store", Toast.LENGTH_LONG).show();
                } else {
                    String currentAddress = (String) selectedStoreAddress.getText();
                    int storeIndex = addresses.indexOf(currentAddress);

                    Intent intent = new Intent(MainPage.this, SubmitActivity.class);
                    intent.putExtra("STORE_INDEX", storeIndex);
                    intent.putExtra("STORE_LIST", restoList);
                    startActivity(intent);
                }
            }
        });
    }}