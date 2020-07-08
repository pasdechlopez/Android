package com.example.github;

import com.example.github.Corona.DoubleRegion;
import com.example.github.Room.UserData;
import com.example.github.modules.network.models.Region;

import java.util.List;

public interface IMainView {
    void onInitialLoadingSuccess(List<UserData> payload);
    void onInitialLoadingFailure(String message);

    void loadNextPageSuccess(List<UserData> users);

    void setCoronaData(List<DoubleRegion> infectedByRegion);
}
