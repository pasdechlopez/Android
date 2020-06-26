package com.example.myapplication;

import android.text.TextWatcher;

import androidx.annotation.NonNull;

import com.example.myapplication.TextChangedTextWatcher;


public class TextWatcherUtils {
    interface TextChangedCallback {
        void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
    }

    public static TextWatcher createTextChangedTextWatcher(@NonNull TextChangedCallback textChangedCallback) {
        return new TextChangedTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textChangedCallback.onTextChanged(charSequence, i, i1, i2);
            }
        };
    }
}
