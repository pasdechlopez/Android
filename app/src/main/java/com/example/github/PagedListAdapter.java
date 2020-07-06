package com.example.github;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PagedListAdapter extends androidx.paging.PagedListAdapter<UserData, UsersViewHolder> {

    protected PagedListAdapter(DiffUtil.ItemCallback<UserData> dataItemCallback) {
        super(dataItemCallback);
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        UsersViewHolder holder = new UsersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
