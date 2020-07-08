package com.example.github.Corona;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.modules.network.models.Region;

public class RegionsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView firstCityName;
    private TextView secondCityName;
    private TextView firstCityInfected;
    private TextView secondCityInfected;

    private RecyclerView recyclerView;

    public RegionsViewHolder(@NonNull View view) {
        super(view);
        this.context = view.getContext();
        this.firstCityName = view.findViewById(R.id.city1_name);
        this.firstCityInfected = view.findViewById(R.id.city1_infected);
        this.secondCityName = view.findViewById(R.id.city1_name);
        this.secondCityInfected = view.findViewById(R.id.city2_infected);
        this.recyclerView = view.findViewById(R.id.recycler_regions);
    }

    public void bind(DoubleRegion region) {
        firstCityName.setText(region.getRegionName());
        secondCityName.setText(region.getRegion1Name());
        firstCityName.setOnClickListener(v -> openCoronaActivity());
        String city = "Infected: " + region.getRegionInfected();
        String city1 = "Infected: " + region.getRegion1Infected();
        secondCityName.setText(region.getRegion1Name());
        secondCityInfected.setText(region.getRegion1Infected());
    }

    private void openCoronaActivity() {
        Intent intent = new Intent(context, CoronaActivity.class);
        context.startActivity(intent);
    }
}
