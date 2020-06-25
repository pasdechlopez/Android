package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  int currentValue = 0;
  TextView textCounter;
  Button buttonCounter;

  private RecyclerView recycler;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView.Adapter adapter = new CustomView.Adapter();
        recycler = findViewById(R.id.recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager((this)));
        recycler.setAdapter(adapter);

  }
}
