package com.example.github;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github.modules.network.IApi;
import com.example.github.modules.network.NetworkModule;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersActivity extends AppCompatActivity implements IMainView {


    MainPresenter presenter;
    GithubAdapter adapter;
    EditText search;
    String login;

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
        api.getFollowers(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(users -> {
            loadNextPageSuccess(users);
        }, throwable -> onInitialLoadingFailure(throwable.getMessage()));
    }


    private void initRecycler() {
        adapter = new GithubAdapter();
        RecyclerView recycler = findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        recycler.setAdapter(adapter);
    }


    private void initToolbar() {
        initBackButton();
        TextView title = findViewById(R.id.toolbar_title);
        String text = login + "'s followers: ";
        title.setText(text);
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

    }
}


