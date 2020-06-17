package com.example.bellerage.test;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(getApplicationContext(), "CREATE", Toast.LENGTH_SHORT).show();
        Log.i("TEST_TAG", "CREATE");
        String test = TestSingleton.getInstance();
    }
}
