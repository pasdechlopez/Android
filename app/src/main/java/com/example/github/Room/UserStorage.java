package com.example.github.Room;

import android.content.Context;
import android.util.Log;

import androidx.paging.PositionalDataSource;

import com.example.github.modules.network.NetworkModule;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserStorage {

    private String id;
    private UsersDB usersDB;
    private Context context;

    public UserStorage(Context context) {
        this.context = context;
    }

    public void loadData(int requestedStartPosition, int requestedLoadSize, PositionalDataSource.LoadInitialCallback<UserData> callback) {
        usersDB = UsersDB.getInstance(context);
        usersDB.userDAO().selectAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<UserData>>() {
                    @Override
                    public void onSuccess(List<UserData> list) {
                        if (list.isEmpty()) {
                            NetworkModule.getApi().getUsers(null)
                                    .subscribeOn(Schedulers.io())
                                    .map(users -> {
                                        usersDB.userDAO().insertAll(users);
                                        return users;
                                    })
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new DisposableSingleObserver<List<UserData>>() {
                                        @Override
                                        public void onSuccess(List<UserData> list) {
                                            callback.onResult(list, 0);
                                            id = list.get(list.size() - 1).id;
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.i("Init Load data error:", e.getMessage());
                                        }
                                    });

                        } else {
                            callback.onResult(list, 0);
                            id = list.get(list.size() - 1).id;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Init DB req error:", e.getMessage());
                    }
                });

    }

    public void loadData(int requestedStartPosition, int requestedLoadSize, PositionalDataSource.LoadRangeCallback<UserData> callback) {
        NetworkModule.getApi().getUsers(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<List<UserData>>() {
            @Override
            public void onSuccess(List<UserData> list) {
                callback.onResult(list);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}


