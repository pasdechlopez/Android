package com.example.github.Corona;

import com.example.github.modules.network.models.Region;

import java.util.ArrayList;
import java.util.List;

public class DoubleRegion {
    public static final int FIRST_REGION_POS = 0;
    public static final int SECOND_REGION_POS = 1;

    private List<Region> regionList = new ArrayList<>(2);

    public List<Region> getRegionList() {
        return regionList;
    }

    public void addRegion(Region region) {
        regionList.add(region);
    }

    public int getRegionsSize() {
        return regionList.size();
    }
}
