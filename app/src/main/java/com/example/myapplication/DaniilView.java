package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DaniilView extends LinearLayout implements IExample {

    private int currentValue;
    private TextView textView;
    private DaniilAdapter adapter;


    public DaniilView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    private void initializeView() {
        inflate(getContext(), R.layout.view_daniil, this);
        initRecycler();
        initTextView();
        initializeButtonAdd1();
        initializeButtonAdd2();
        initializeButtonSubtract3();
    }

    private void initRecycler() {
        adapter = new DaniilAdapter();
        RecyclerView recycler = findViewById(R.id.recyclerview);
        LinearLayoutManager linear = new LinearLayoutManager(getContext());
        linear.setReverseLayout(true);
        recycler.setLayoutManager(linear);
        recycler.setAdapter(adapter);
    }

    private void initTextView() {
        textView = findViewById(R.id.text_view);
        textView.setText(R.string.init_value);
    }

    private void initializeButtonAdd1() {
        Button buttonAdd1 = findViewById(R.id.button_add1);
        buttonAdd1.setOnClickListener(this::onClickAdd1);
    }

    private void initializeButtonAdd2() {
        Button buttonAdd2 = findViewById(R.id.button_add2);
        buttonAdd2.setOnClickListener(this::onClickAdd2);
    }

    private void initializeButtonSubtract3() {
        Button buttonSubtract3 = findViewById(R.id.button_subtract3);
        buttonSubtract3.setOnClickListener(this::onClickSubtract3);
    }

    @Override
    public String getValue() {
        return textView.getText().toString();
    }

    public boolean isNumeric(String string) {
        return string != null && string.matches("[-+]?\\d*\\.?\\d+");
    }

    @Override
    public void setupValue(String value) {
        if (isNumeric(value)) {
            currentValue = Integer.parseInt(value);
            textView.setText(value);
            adapter.clearAll();
        } else {
            Log.i("ERROR", "Input error: incorrect incoming data");
        }
    }

    @Override
    public void clearValue() {
        currentValue = 0;
        String string = String.valueOf(currentValue);
        textView.setText(string);
        addRecyclerItem(getResources().getString(R.string.drop_value));
    }

    public void onClickAdd1(View view) {
        currentValue++;
        addRecyclerItem(1);
        styleRecyclerItem(1);
        String string = String.valueOf(currentValue);
        textView.setText(string);
    }

    public void onClickAdd2(View view) {
        currentValue += 2;
        addRecyclerItem(2);
        styleRecyclerItem(2);
        String string = String.valueOf(currentValue);
        textView.setText(string);
    }

    public void onClickSubtract3(View view) {
        currentValue -= 3;
        addRecyclerItem(-3);
        styleRecyclerItem(-3);
        String string = String.valueOf(currentValue);
        textView.setText(string);
    }

    public void addRecyclerItem(int value) {
        int prevValue = currentValue - value;
        String output = prevValue + "+" + value + "=" + currentValue;
        RecyclerItem item = new RecyclerItem(output, styleRecyclerItem(value));
        adapter.addItem(item);
    }

    public void addRecyclerItem(String value) {
        RecyclerItem item = new RecyclerItem(value, styleRecyclerItem(Integer.parseInt(value)));
        adapter.addItem(item);
    }

    public int styleRecyclerItem(int value) {
        int style = value > 0 ? 1 : 2;
        return style;
    }

}

