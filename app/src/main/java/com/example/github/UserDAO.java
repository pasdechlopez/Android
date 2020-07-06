package com.example.github;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM UsersData")
    Flowable<List<UserData>> selectAll();

    @Query("SELECT * FROM UsersData WHERE id = :id")
    UserData getUserByID(String id);

    @Insert
    void insert(UserData userData);

    @Insert
    void insertAll(List<UserData> list);

    @Delete
    void deleteUser(UserData userData);

    @Query("DELETE FROM UsersData")
    void deleteAll();
}
