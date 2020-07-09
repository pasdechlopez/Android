package com.example.github.Corona;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;

import java.util.List;

public class CoronaActivity extends AppCompatActivity implements ICoronaActivity {

    private CoronaActivityAdapter coronaInfoAdapter;
    private CoronaActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetached();
        super.onDestroy();
    }

    private void initView() {
        initPresenter();
        initAdapter();
        initRecycler();
        initToolBar();
    }

    private void initToolBar() {
        final ImageView backButton = findViewById(R.id.corona_backbutton);
        backButton.setOnClickListener(this::finishCoronaActivity);
    }

    private void finishCoronaActivity(View view) {
        finish();
    }

    private void initPresenter() {
        presenter = new CoronaActivityPresenter(this);
    }

    private void initAdapter() {
        coronaInfoAdapter = new CoronaActivityAdapter();
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_corona);
        recyclerView.setAdapter(coronaInfoAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setCoronaInfo(List<CoronaInfoItem> coronaInfoItemList) {
        coronaInfoAdapter.setItems(coronaInfoItemList);
    }
}
