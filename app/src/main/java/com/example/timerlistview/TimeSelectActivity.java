package com.example.timerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimeSelectActivity extends AppCompatActivity {

    public final static String KEY = "KEY";
    int numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);


        ListView listView = findViewById(R.id.listView);

        List<String> list = new ArrayList<>();


        for (int i = 0; i < 60; i++) {
            list.add(String.valueOf(i));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(TimeSelectActivity.this, MainActivity.class);
                i.putExtra(KEY, position);
                setResult(RESULT_OK, i);
                finish();
            }
        });


    }

}