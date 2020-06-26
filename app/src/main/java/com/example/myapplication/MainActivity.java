package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButtonKiller();
        initalizeEditText();
    }

    private void initializeButtonKiller() {
        IExample myView = findViewById(R.id.daniil_view);
        Button buttonKiller = findViewById(R.id.test_killer_button);
        buttonKiller.setOnClickListener(view -> myView.clearValue());
    }

    private void initalizeEditText() {
        EditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(TextWatcherUtils.createTextChangedTextWatcher(this::handleOnTextChanged));
    }

    private void handleOnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        IExample myView = findViewById(R.id.daniil_view);
        myView.setupValue(charSequence.toString());
    }

}
