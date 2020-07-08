package com.example.github;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.github.Room.UsersDB;
import com.example.github.modules.network.IApi;
import com.example.github.modules.network.NetworkModule;

import java.util.concurrent.Executor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class MainPresenter {

    public UsersDB usersDB;

    private final CompositeDisposable disposables = new CompositeDisposable();
    IMainView view;
    private boolean fetching = false;
    private Executor executor;
    private PagedListAdapter pagedListAdapter;


    public MainPresenter(IMainView view, Context context) {
        this.view = view;
//        initDB(context);
        initPagedList(context);
    }

    private void initPagedList(Context context) {

    }

    private void initDB(Context context) {
        usersDB = UsersDB.getInstance(context);
        disposables.add(usersDB.userDAO()
                .selectAll()
                .subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(userData -> {
                            if (!userData.isEmpty()) {
                                view.onInitialLoadingSuccess(userData);
                            } else {
                                getUsers();
                            }
                        }));
    }

    public void getUsers() {
        IApi api = NetworkModule.getApi();
        disposables.add(api.getUsers(null).subscribeOn(Schedulers.io())
                .map(users -> {
                    usersDB.userDAO().insertAll(users);
                    Log.i("User + id: ", usersDB.userDAO().selectAll().toString());
                    return users;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> view.onInitialLoadingSuccess(users),
                        throwable -> {
                            view.onInitialLoadingFailure(throwable.getMessage());
                        }
                ));
    }

    public void getUsers(String id) {
        if (!fetching) {
            fetching = true;
            IApi api = NetworkModule.getApi();
            disposables.add(
                    api.getUsers(id)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    users -> {
                                        usersDB.userDAO().insertAll(users);
                                        fetching = false;
                                    },
                                    Throwable::printStackTrace
                            )
            );
        }
//        disposables.add(api.getUsers(id).subscribeOn(Schedulers.io()).subscribe(us))
//                .subscribe(us);
//                .subscribe(users -> {
//                    usersDB.userDAO().insertAll(users);
//                    Log.i("User + id: ", usersDB.userDAO().selectAll().toString());
//                    return users;
//                });
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        users -> view.onInitialLoadingSuccess(users),
//                        throwable -> {
//                            view.onInitialLoadingFailure(throwable.getMessage());
//                        }
//                ));
    }

    private void getUsersFromDB(String id) {
        for (int i = 0; i < 10 + Integer.parseInt(id); i++) {
            usersDB.userDAO().getUserByID(String.valueOf(i));
        }
    }

    @SuppressLint("CheckResult")
    private void loadNextUsers(String id) {
        IApi api = NetworkModule.getApi();
        api.getUsers(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(users -> view.loadNextPageSuccess(users), throwable -> {
                    view.onInitialLoadingFailure(throwable.getMessage());
                }
        );
    }

    public void onDestroy() {
        disposables.dispose();
    }
}
