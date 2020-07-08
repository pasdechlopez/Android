package com.example.github.MVVM;

import com.example.github.Room.UserData;

import java.util.List;

import io.reactivex.Observable;

public interface IMainViewModel {
    Observable<List<UserData>> getUsersObservable();

    void loadNextPage(String s);
}
