package com.example.github;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class UsersViewHolder extends RecyclerView.ViewHolder {

    private TextView userLogin;
    private TextView userId;
    private Context context;
    private ImageView userImage;
    private TextView userFollowers;
    private LinearLayout wrapper;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        userLogin = itemView.findViewById(R.id.user_login);
        userId = itemView.findViewById(R.id.user_id);
        userImage = itemView.findViewById(R.id.user_image);
        wrapper = itemView.findViewById(R.id.wrapper);
        userFollowers = itemView.findViewById(R.id.user_followers);
    }

    public void bind(UserData user) {
        userLogin.setText(context.getString(R.string.login, user.login));
        userFollowers.setText(Boolean.valueOf(user.followers) ? user.followers : null);
        userId.setText(context.getString(R.string.id, user.id));
        wrapper.setOnClickListener(v -> openFollowersActivity(user.login));
        Picasso.get().load(user.avatar_url).into(userImage);
    }

    public void openFollowersActivity(String login) {
        Intent intent = new Intent(context, FollowersActivity.class);
        intent.putExtra("login", login);
        context.startActivity(intent);
    }

}
