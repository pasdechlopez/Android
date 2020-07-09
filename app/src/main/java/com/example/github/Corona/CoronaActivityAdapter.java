package com.example.github.Corona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;

import java.util.ArrayList;
import java.util.List;

public class CoronaActivityAdapter extends RecyclerView.Adapter<CoronaViewHolder> {

    final private List<CoronaInfoItem> coronaInfoList;

    public CoronaActivityAdapter() {
        coronaInfoList = new ArrayList<>(5);
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.corona, parent, false);
        return new CoronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder holder, int position) {
        CoronaInfoItem coronaInfoItem = coronaInfoList.get(position);
        holder.bind(coronaInfoItem);
    }

    @Override
    public int getItemCount() {
        return coronaInfoList.size();
    }

    public void setItems(List<CoronaInfoItem> coronaInfoItems) {
        coronaInfoList.clear();
        coronaInfoList.addAll(coronaInfoItems);
        notifyDataSetChanged();
    }

}
