package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class CustomView extends LinearLayout implements IExample {

  private static final String orientation = "HORIZONTAL";
  private String value = "START_VALUE";
  private int count;
  private TextView textView;

  private RecyclerView recycler;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;

  private String[] Data;

  @Nullable
  private EditText editText;

  @Nullable
  private Button buttonAdd1;
  private Button buttonAdd2;
  private Button buttonSubtract3;


//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//
//    buttonAdd1 = findViewById(R.id.button_add1);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//          .setAction("Action", null).show();
//      }
//    });
//  }

  public class Adapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
      return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
      return 10;
    }
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
//    initializeExampleBtn(getOrientation());
  }

  private void initializeButtonAdd1(int orientation) {
    buttonAdd1 = findViewById(R.id.button_add1);
    if (buttonAdd1 != null) {
      buttonAdd1.setOnClickListener(this::onClickAdd1);
//      if (orientation == 'HORIZONTAL') {
//        setupHorizontalParams(buttonAdd1);
//      }
    }

  }

  private void initializeButtonAdd2(int orientation) {
    buttonAdd2 = findViewById(R.id.button_add2);
    if (buttonAdd2 != null) {
      buttonAdd2.setOnClickListener(this::onClickAdd2);
//      if (orientation == 'HORIZONTAL') {
//        setupHorizontalParams(buttonAdd1);
//      }
    }

  }

  private void initializeButtonSubtract3(int orientation) {
    buttonSubtract3 = findViewById(R.id.button_subtract3);
    if (buttonSubtract3 != null) {
      buttonSubtract3.setOnClickListener(this::onClickSubtract3);
//      if (orientation == 'HORIZONTAL') {
//        setupHorizontalParams(buttonAdd1);
//      }
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
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
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
    count = 0;
    if (textView != null) {
      textView.setText("0");
      recycler = findViewById(R.id.recyclerview);
    }
  }

  public void onClickAdd1(View view) {
    count++;
    textView = findViewById(R.id.text_view);
    textView.setText("Current Value:" + count);
  }
  public void onClickAdd2(View view) {
    count += 2;
    textView = findViewById(R.id.text_view);
    textView.setText("Current Value:" + count);
  }
  public void onClickSubtract3(View view) {
    count -= 3;
    textView = findViewById(R.id.text_view);
    textView.setText("Current Value:" + count);
  }
}

