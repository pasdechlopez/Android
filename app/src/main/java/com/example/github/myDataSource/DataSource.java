package com.example.github.myDataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.github.UserData;
import com.example.github.UserStorage;

import java.util.List;

public class DataSource extends PositionalDataSource<UserData> {
    private final UserStorage userStorage;

    public DataSource(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<UserData> callback) {
        Log.i("INITIAL:", String.valueOf(params.requestedStartPosition));

        userStorage.loadData(params.requestedStartPosition, params.requestedLoadSize, callback);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<UserData> callback) {
        Log.i("INITIAL:", String.valueOf(params.startPosition));
        userStorage.loadData(params.startPosition, params.loadSize, callback);
    }
}
