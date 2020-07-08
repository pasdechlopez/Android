package com.example.github;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.github.Room.UsersDB;
import com.facebook.stetho.Stetho;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    public static void initDB(Context context) {
        UsersDB usersDB = Room.databaseBuilder(context, UsersDB.class, "database")
                .build();
    }


}
