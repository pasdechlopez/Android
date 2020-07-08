package com.example.github.Corona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.modules.network.models.Region;

import java.util.ArrayList;
import java.util.List;

public class CoronaListAdapter extends RecyclerView.Adapter<RegionsViewHolder> {

    final private List<DoubleRegion> regionList;

    public CoronaListAdapter() {
        regionList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RegionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.region, parent, false);
        return new RegionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionsViewHolder holder, int position) {
        DoubleRegion region = regionList.get(position);
        holder.bind(region);
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public void setItems(List<DoubleRegion> regions) {
        regionList.clear();
        regionList.addAll(regions);
        notifyDataSetChanged();
    }
}
