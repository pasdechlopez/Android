package com.example.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.BreakIterator;

public class CustomView extends LinearLayout implements IExample {

  private String value = "START_VALUE";
  private int count;

  @Nullable
  private EditText editText;

  @Nullable
  private Button buttonCounter;

  public CustomView(Context context) {
    super(context);
    initializeView();
  }

  public CustomView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initializeView();
  }

  public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializeView();
  }

  private void initializeView() {
    inflate(getContext(), R.layout.activity_daniilview, this);
    initializeExampleEt(getOrientation());
    initializeExampleBtn(getOrientation());
  }

  private void initializeExampleBtn(int orientation) {
    buttonCounter = findViewById(R.id.button_add1);
    if (buttonCounter != null) {
      buttonCounter.setOnClickListener(v -> clearValue());
      if (orientation == HORIZONTAL) {
        setupHorizontalParams(buttonCounter);
      }
    }

  }

  private void initializeExampleEt(int orientation) {
    editText = findViewById(R.id.edit_text);

    if (editText != null) {
      editText.setText(value);
      editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
          value = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
      });

      if (orientation == HORIZONTAL) {
        setupHorizontalParams(editText);
      }
    }
  }

  private void setupHorizontalParams(View view) {
    LinearLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
    params.width = 0;
    params.weight = 1;
    view.setLayoutParams(params);
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public void setupValue(@NonNull String value) {
    if (editText != null) {
      editText.setText(value);
    }
  }

  @Override
  public void clearValue() {
    if (editText != null) {
      editText.setText("");
    }
  }

  public  void onClick(View view) {
    count++;
    TextView textView = findViewById(R.id.text_view);
    text.setText("Current Count:" + count)
  }
}

