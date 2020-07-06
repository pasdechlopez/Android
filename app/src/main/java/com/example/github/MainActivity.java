package com.example.github;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter presenter;
    private GithubAdapter adapter;
    private RecyclerView recycler;
    private String id;
    private Executor executor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initView() {
        initToolbar();
        initPagedList();
//        initPresenter();
        initRecycler();

    }

    private void initPagedList() {
        MyPositionalDataSource dataSource = new MyPositionalDataSource(new UserStorage());
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();
        PagedList<UserData> pagedList = new PagedList.Builder<>(dataSource, config)
                .setFetchExecutor(executor)
                .setNotifyExecutor(executor)
                .build();

    }

    private void initPresenter() {
        presenter = new MainPresenter(this, this);
    }

    private void initRecycler() {
        adapter = new GithubAdapter();
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recycler.canScrollVertically(1)) {
                    Log.i("STATE_CHANGE", String.valueOf(newState));
                    presenter.getUsers(adapter.getID());
                }
            }
        });

    }

    public String getId() {
        return adapter.getID();
    }

    @Override
    public void onInitialLoadingSuccess(List<UserData> payload) {
//        id = adapter.getID();
        adapter.setUsers(payload);
    }


    @Override
    public void onInitialLoadingFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
        Log.i("ERROR MESSAGE: ", message);
    }

    @Override
    public void loadNextPageSuccess(List<UserData> users) {
        String currentID = adapter.getID();
        if (currentID != null && !currentID.equals(id)) {
            adapter.addUsers(users);
        }
    }

    private void initToolbar() {
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.app_name));
    }

}
