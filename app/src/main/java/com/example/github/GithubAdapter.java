package com.example.github;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    final private List<UserData> usersList;


    public GithubAdapter() {
        usersList = new ArrayList<>();
    }

    public void setUsers(List<UserData> users) {
        usersList.clear();
        usersList.addAll(users);
        notifyDataSetChanged();
        Log.i("ADAPTER: USERS: ", users.toString());
    }

    public void setUser(UserData user) {
        usersList.clear();
        usersList.add(user);
        notifyDataSetChanged();
    }

    @Nullable
    public String getID() {
        return usersList.size() > 0 ? usersList.get(usersList.size() - 1).id : null;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position) {
        UserData user = usersList.get(position);
        usersViewHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void addUsers(List<UserData> users) {
        int startPosition = usersList.size();
        usersList.addAll(users);
        notifyItemRangeInserted(startPosition, users.size());
    }
}
