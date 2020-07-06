package com.example.github;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMainViewModel {
    Observable<List<UserData>> getUsersObservable();

    void loadNextPage(String s);
}
