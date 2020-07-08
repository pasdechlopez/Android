package com.example.github.Room;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.github.Room.UserData;
import com.example.github.Room.UserStorage;

public class DataSource extends PositionalDataSource<UserData> {
    private final UserStorage userStorage;

    public DataSource(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<UserData> callback) {
        userStorage.loadData(params.requestedStartPosition, params.requestedLoadSize, callback);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<UserData> callback) {
        userStorage.loadData(params.startPosition, params.loadSize, callback);
    }
}
