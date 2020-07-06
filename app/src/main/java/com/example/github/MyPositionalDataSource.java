package com.example.github;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import java.util.List;

public class MyPositionalDataSource extends PositionalDataSource<UserData> {
    private final UserStorage userStorage;

    public MyPositionalDataSource(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<UserData> callback) {
        List<UserData> list = userStorage.getData(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(list, 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<UserData> callback) {
        List<UserData> list = userStorage.getData(params.startPosition, params.loadSize);
        callback.onResult(list);
    }
}
