package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        cityList = findViewById(R.id.city_list);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonDelete = findViewById(R.id.button_delete);
        Button buttonConfirm = findViewById(R.id.button_confirm);
        EditText editTextName = findViewById(R.id.editText_name);
        LinearLayout addCityLayout = findViewById(R.id.add_city_layout);

        // Initial Data from screenshots
        String[] cities = {"Edmonton", "Montréal"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Set up Adapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Selection Logic
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        // ADD CITY Button: Show the input field at the bottom
        buttonAdd.setOnClickListener(v -> {
            addCityLayout.setVisibility(View.VISIBLE);
        });

        // CONFIRM Button: Add new city and hide input field
        buttonConfirm.setOnClickListener(v -> {
            String cityName = editTextName.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                editTextName.setText("");
                addCityLayout.setVisibility(View.GONE);
            }
        });

        // DELETE CITY Button: Remove selected city
        buttonDelete.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition < dataList.size()) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                // Reset selection
                cityList.clearChoices();
                selectedPosition = -1;
            }
        });
    }
}