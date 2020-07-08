package com.example.github;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.Corona.CoronaListAdapter;
import com.example.github.Corona.CoronaPresenter;
import com.example.github.Corona.DoubleRegion;
import com.example.github.Corona.ICorona;
import com.example.github.R;

import java.util.List;
import java.util.zip.Inflater;

public class CustomViewGroup extends FrameLayout implements ICorona {

    private CoronaListAdapter coronaListAdapter;

    public CustomViewGroup(Context context) {
        super(context);
        inflate(context, R.layout.custom_view, this);
        initCorona();
    }

    private void initCorona() {
        CoronaPresenter presenter = new CoronaPresenter(this);
        coronaListAdapter = new CoronaListAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_regions);
        recyclerView.setAdapter(coronaListAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setCoronaData(List<DoubleRegion> doubleRegions) {
        coronaListAdapter.setItems(doubleRegions);
    }
}
