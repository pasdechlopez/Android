package com.example.github;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {
    private final Handler customHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable runnable) {
        customHandler.post(runnable);
    }
}
