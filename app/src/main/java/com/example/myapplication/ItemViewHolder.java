package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textInstance;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textInstance = itemView.findViewById(R.id.recycler_item);
    }

    public void bind(RecyclerItem item) {
        textInstance.setText(item.name);
        int fontColor = textInstance.getContext().getResources().getColor(R.color.acid_green);
        int backgroundColor = textInstance.getContext().getResources().getColor(R.color.colorPrimaryDark);
        textInstance.setTextColor(item.style == 1 ? fontColor : 0);
        textInstance.setBackgroundColor(item.style == 2 ? backgroundColor : 0);
    }



}