package com.example.github.Corona;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.github.R;
import com.example.github.databinding.CoronaBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoronaViewHolder extends RecyclerView.ViewHolder {
    public CoronaViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(CoronaInfoItem coronaInfoItem) {
        final CoronaBinding bind = CoronaBinding.bind(itemView);

        bind.coronaActivityDesc.setText(coronaInfoItem.getDescription());
        bind.coronaActivityName.setText(coronaInfoItem.getTitle());

        final ImageView imageView = itemView.findViewById(R.id.button_link);
        imageView.setOnClickListener(v -> openLink(coronaInfoItem.getUrl()));
    }

    private void openLink(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        itemView.getContext().startActivity(intent);
    }
}
