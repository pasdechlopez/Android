package com.example.github.Corona;

import com.example.github.modules.network.CoronaApi;
import com.example.github.modules.network.NetworkModule;
import com.example.github.modules.network.models.Region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoronaViewPresenter {
    private ICorona view;
    private Disposable disposable;

    public CoronaViewPresenter(ICorona view) {
        this.view = view;
        getData();
    }

    public void getData() {
        CoronaApi api = NetworkModule.getCoronaApi();
        disposable = api.getCities(true)
                .subscribeOn(Schedulers.io())
                .map(coronaStatistics -> handleRegions(coronaStatistics.getInfectedByRegion()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessDoubleRegions, Throwable::printStackTrace);
    }

    private void handleSuccessDoubleRegions(List<DoubleRegion> doubleRegions) {
        view.setCoronaData(doubleRegions);
        disposable.dispose();
    }

    private List<DoubleRegion> handleRegions(List<Region> regions) {
        sortRegions(regions);
        return createDoubleRegionsList(regions);
    }

    private List<DoubleRegion> createDoubleRegionsList(List<Region> regions) {
        final List<DoubleRegion> doubleRegionList = new ArrayList<>();
        DoubleRegion tempDoubleRegion = new DoubleRegion();
        for (Region region : regions) {
            if (tempDoubleRegion.getRegionsSize() < 2) {
                tempDoubleRegion.addRegion(region);
            } else {
                doubleRegionList.add(tempDoubleRegion);
                tempDoubleRegion = new DoubleRegion();
                tempDoubleRegion.addRegion(region);
            }
        }
        return doubleRegionList;
    }

    private void sortRegions(List<Region> regions) {
        final Comparator<Region> comparator = (region, region1) ->
                (-1) * Integer.compare(
                        region.getInfected(),
                        region1.getInfected()
                );

        Collections.sort(regions, comparator);
    }

    public void onDetached() {
        view = null;
    }
}
