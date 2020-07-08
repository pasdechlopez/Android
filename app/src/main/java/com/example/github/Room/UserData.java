package com.example.github.Room;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UsersData")
public
class UserData {
    public static final DiffUtil.ItemCallback<UserData> CALLBACK = new DiffUtil.ItemCallback<UserData>() {

        @Override
        public boolean areItemsTheSame(@NonNull UserData oldItem, @NonNull UserData newItem) {
            return false;
        }
        @Override
        public boolean areContentsTheSame(@NonNull UserData oldItem, @NonNull UserData newItem) {
            return false;
        }
    };

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
    }
}
