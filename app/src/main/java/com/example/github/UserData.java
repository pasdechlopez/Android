package com.example.github;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "UsersData")
public
class UserData {
    @PrimaryKey(autoGenerate = true)
    public int number;
    @NonNull
    public String id;
    @ColumnInfo(name = "login")
    public String login;
    @ColumnInfo(name = "avatar_url")
    public String avatar_url;
    @ColumnInfo(name = "followers")
    public String followers;


    public UserData(String id, String login, String followers, String avatar_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.followers = followers;
//        this.avatar_url = user.avatar_url;
//        this.followers = user.followers;
    }

//    @Ignore
//    public UserData(String followers, String avatar_url) {
//        this.followers = followers;
//        this.avatar_url = avatar_url;
//    }
}
