package com.example.github.Corona;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.databinding.RegionBinding;
import com.example.github.modules.network.models.Region;

import static com.example.github.Corona.DoubleRegion.FIRST_REGION_POS;
import static com.example.github.Corona.DoubleRegion.SECOND_REGION_POS;

public class RegionsViewHolder extends RecyclerView.ViewHolder {

    public RegionsViewHolder(@NonNull View view) {
        super(view);
    }

    public void bind(DoubleRegion doubleRegion) {
        final RegionBinding binding = RegionBinding.bind(itemView);

        final Region region1 = doubleRegion.getRegionList().get(FIRST_REGION_POS);
        final Region region2 = doubleRegion.getRegionList().get(SECOND_REGION_POS);

        final String region1InfectedText = "Infected: " + region1.getInfected();
        final String region2InfectedText = "Infected: " + region2.getInfected();

        binding.city1Name.setText(region1.getRegion());
        binding.city1Infected.setText(region1InfectedText);
        binding.city2Name.setText(region2.getRegion());
        binding.city2Infected.setText(region2InfectedText);

        itemView.setOnClickListener(this::openCoronaActivity);
    }

    private void openCoronaActivity(View view) {
        final Intent intent = new Intent(view.getContext(), CoronaActivity.class);
        view.getContext().startActivity(intent);
    }
}
