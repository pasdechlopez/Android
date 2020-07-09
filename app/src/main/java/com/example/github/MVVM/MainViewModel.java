package com.example.github.MVVM;

import com.example.github.Room.UserData;
import com.example.github.modules.network.IApi;
import com.example.github.modules.network.NetworkModule;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MainViewModel implements IMainViewModel {

    Subject<List<UserData>> usersObservable = PublishSubject.create();


    @Override
    public Observable<List<UserData>> getUsersObservable() {
        return usersObservable;
    }

    @Override
    public void loadNextPage(String id) {
        IApi api = NetworkModule.getApi();
        api.getUsers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> usersObservable.onNext(users), throwable -> {
                        }
                );
    }
}
