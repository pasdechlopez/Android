package com.example.github.Corona;

import com.example.github.modules.network.models.Region;

public class DoubleRegion {

    private Region region;
    private Region region1;

    public DoubleRegion(Region region, Region region1) {
        this.region = region;
        this.region1 = region1;
    }

    public String getRegionName() {
        return region.getRegion();
    }
    public int getRegionInfected() {
        return region.getInfected();
    }

    public int getRegion1Infected() {
        return region.getInfected();
    }

    public String getRegion1Name() {
        return region1.getRegion();
    }
}
