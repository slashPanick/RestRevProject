package com.example.RestRevProj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    ListView storeList;
    ArrayList<String> stores = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        storeList = findViewById(R.id.storeList);
        searchEditText = findViewById(R.id.searchEditText);

        stores.add("McDonalds");
        stores.add("Burger King");
        stores.add("Pizza Hut");
        stores.add("Dominoes");
        stores.add("Wendys");
        stores.add("Dairy Queen");
        stores.add("Burrito Jax");
        stores.add("Boston Pizza");

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stores);
        storeList.setAdapter(arrayAdapter);

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
    }
}