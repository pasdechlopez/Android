package com.example.github.Corona;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.example.github.IMainView;
import com.example.github.modules.network.CoronaApi;
import com.example.github.modules.network.NetworkModule;
import com.example.github.modules.network.models.CoronaStatistics;
import com.example.github.modules.network.models.Region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CoronaPresenter {
    private ICorona view;

    public CoronaPresenter(ICorona view) {
        this.view = view;
        getData();
    }

    private CompositeDisposable disposable = new CompositeDisposable();

    public void getData() {
        CoronaApi api = NetworkModule.getCoronaApi();
        api.getCities(true)
                .subscribeOn(Schedulers.io())
                .map(coronaStatistics -> {

                    final List<Region> regions = coronaStatistics.getInfectedByRegion();
                    if (regions.size() % 2 != 0) {
                        regions.remove(regions.size() - 1);
                    }
                    List<DoubleRegion> doubleRegionList = new ArrayList<>();
                    for (int i = 0; i < regions.size(); i++) {
                        DoubleRegion doubleRegion = new DoubleRegion(regions.get(i), regions.get(i + 1));
                        doubleRegionList.add(doubleRegion);
                    }

                    Collections.sort(regions, (region, region1) -> Integer.compare(region.getInfected(), region1.getInfected()));
                    return doubleRegionList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<DoubleRegion>>() {
                    @Override
                    public void onSuccess(List<DoubleRegion> doubleRegions) {
                        view.setCoronaData(doubleRegions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
