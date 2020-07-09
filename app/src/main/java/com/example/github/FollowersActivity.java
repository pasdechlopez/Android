package com.example.github;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.Corona.DoubleRegion;
import com.example.github.Room.UserData;
import com.example.github.modules.network.IApi;
import com.example.github.modules.network.NetworkModule;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FollowersActivity extends AppCompatActivity implements IMainView {


    GithubAdapter adapter;
    String login;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        initView();
    }

    private void initView() {
        initRecycler();
        getProps();
        initToolbar();
        getFollowers(login);

    }

    private void getProps() {
        login = getIntent().getExtras().getString("login");
    }


    @SuppressLint("CheckResult")
    private void getFollowers(String login) {

        IApi api = NetworkModule.getApi();
        String url = "users/" + login + "/followers";
        api.getFollowers(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadNextPageSuccess, throwable ->
                        onInitialLoadingFailure(throwable.getMessage()));
    }


    private void initRecycler() {
        adapter = new GithubAdapter();
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        recycler.setAdapter(adapter);
    }


    private void initToolbar() {
        initBackButton();
        initUpButton();
        TextView title = findViewById(R.id.toolbar_title);
        String text = login + "'s followers: ";
        title.setText(text);

    }

    private void initUpButton() {
        ImageButton backButton = findViewById(R.id.button_up);
        backButton.setOnClickListener(v -> scrollUp());
        backButton.setRotation(-90);
    }

    private void scrollUp() {
        recycler.smoothScrollToPosition(0);
    }


    private void initBackButton() {
        ImageButton backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Close Activity?")
                .setPositiveButton("YES", (dialog1, which) -> finish())
                .setNegativeButton("NO", (dialog12, which) -> {
                })
                .create();
        dialog.show();
    }

    @Override
    public void onInitialLoadingSuccess(List<UserData> payload) {
        adapter.setUsers(payload);
    }

    @Override
    public void onInitialLoadingFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void loadNextPageSuccess(List<UserData> users) {
        adapter.setUsers(users);
    }

    @Override
    public void setCoronaData(List<DoubleRegion> infectedByRegion) {

    }

}


