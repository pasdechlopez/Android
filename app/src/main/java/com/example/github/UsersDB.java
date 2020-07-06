package com.example.github;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserData.class, version = 1, exportSchema = false)

public abstract class UsersDB extends RoomDatabase {
    private static final String DBName = "UsersData";
    private static UsersDB instance;

    public static synchronized UsersDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UsersDB.class, DBName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
