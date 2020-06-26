package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DaniilAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    final private List<RecyclerItem> recyclerItems;

    public DaniilAdapter() {
        recyclerItems = new ArrayList<>();
    }

    public void setItems(List<RecyclerItem> items) {
        recyclerItems.clear();
        recyclerItems.addAll(items);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        RecyclerItem item = recyclerItems.get(position);
        itemViewHolder.bind(item);
    }

    public void addItem(RecyclerItem item) {
        recyclerItems.add(0, item);
        notifyItemInserted(0);
    }

    public void clearAll() {
        recyclerItems.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        recyclerItems.remove(position);
        notifyItemRemoved(position);
    }

    public void addItems(List<RecyclerItem> items) {
        recyclerItems.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }
}