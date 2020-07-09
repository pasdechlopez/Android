package com.example.github.Corona;

import com.example.github.R;
import com.example.github.modules.network.CoronaApi;
import com.example.github.modules.network.NetworkModule;
import com.example.github.modules.network.models.CoronaStatistics;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CoronaActivityPresenter {
    private ICoronaActivity view;

    public CoronaActivityPresenter(ICoronaActivity view) {
        this.view = view;
        getFalseData();
    }


    public void getFalseData() {
        CoronaApi api = NetworkModule.getCoronaApi();
        api.getCity(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<CoronaStatistics>() {
                    @Override
                    public void onSuccess(CoronaStatistics coronaStatistics) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setCoronaInfo(createCoronaInfo());
                    }
                });
    }

    private List<CoronaInfoItem> createCoronaInfo() {
        final String description = view.getResources().getString(R.string.text);
        final String title = view.getResources().getString(R.string.title);
        final String url = "https://tproger.ru/video/non-euclidean-game-engine/?autoplay=1";
        List<CoronaInfoItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new CoronaInfoItem(title, description, url));
        }

        return list;
    }


    public void onDetached() {
        view = null;
    }
}
