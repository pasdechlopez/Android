package com.example.github;

import java.util.List;

interface IMainView {
    void onInitialLoadingSuccess(List<UserData> payload);
    void onInitialLoadingFailure(String message);

    void loadNextPageSuccess(List<UserData> users);
}
