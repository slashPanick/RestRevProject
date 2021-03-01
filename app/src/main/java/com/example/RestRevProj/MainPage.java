// did example of ArrayList working with ListView

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
    ArrayList<String> stores = new ArrayList<>();           //start with empty arraylist
    ArrayAdapter<String> arrayAdapter;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        storeList = findViewById(R.id.storeList);
        searchEditText = findViewById(R.id.searchEditText);

        stores.add("McDonalds");                            // add items to that arraylist
        stores.add("Burger King");
        stores.add("Pizza Hut");
        stores.add("Dominoes");
        stores.add("Wendys");
        stores.add("Dairy Queen");
        stores.add("Burrito Jax");
        stores.add("Boston Pizza");

        // this is required for ListView to function. params are just context, the ListView, the item (text1) in the ListView,
        // and what is going in it (stores array). then the adapter is set as the one used by storeList ListView
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
    }
}