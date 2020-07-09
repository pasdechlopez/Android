package com.example.github.Corona;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import com.example.github.R;

import java.util.List;

public class CoronaView extends CardView implements ICorona {

    private CoronaViewAdapter coronaViewAdapter;
    private CoronaViewPresenter presenter;

    public CoronaView(@NonNull Context context) {
        super(context);
    }

    public CoronaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoronaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initCorona() {
        setRadius(0);
        inflate(getContext(), R.layout.custom_view, this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initCorona();

        presenter = new CoronaViewPresenter(this);
        coronaViewAdapter = new CoronaViewAdapter();

        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_regions);
        recyclerView.setAdapter(coronaViewAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setCoronaData(List<DoubleRegion> doubleRegions) {
        coronaViewAdapter.setItems(doubleRegions);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.onDetached();
        super.onDetachedFromWindow();
    }
}
