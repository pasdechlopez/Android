package com.example.github;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.Corona.CoronaViewAdapter;
import com.example.github.Corona.DoubleRegion;
import com.example.github.PagedLib.MainThreadExecutor;
import com.example.github.PagedLib.PagedListAdapter;
import com.example.github.Room.DataSource;
import com.example.github.Room.UserData;
import com.example.github.Room.UserStorage;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter presenter;
    private GithubAdapter adapter;
    private CoronaViewAdapter coronaViewAdapter;
    private RecyclerView recycler;
    private String id;
    private PagedListAdapter pagedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onDestroy() {
//        presenter.onDestroy();
        super.onDestroy();
    }

    private void initView() {
        initToolbar();
//        initCorona();
//        initPresenter();
//        initRecycler();
        initPagedList();

    }


    @Override
    public void onInitialLoadingSuccess(List<UserData> payload) {

    }

    @Override
    public void onInitialLoadingFailure(String message) {

    }

    @Override
    public void loadNextPageSuccess(List<UserData> users) {

    }

    public void setCoronaData(List<DoubleRegion> regions) {
        coronaViewAdapter.setItems(regions);
    }


    private void initPagedList() {
        DataSource dataSource = new DataSource(new UserStorage(this));
        MainThreadExecutor executor = new MainThreadExecutor();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(19)
                .build();

        PagedList<UserData> pagedList = new PagedList.Builder<>(dataSource, config)
                .setNotifyExecutor(executor)
                .setFetchExecutor(executor)
                .build();
        initPagedListAdapter(pagedList);
    }

    private void initPagedListAdapter(PagedList<UserData> pagedList) {
        pagedListAdapter = new PagedListAdapter(UserData.CALLBACK);
        pagedListAdapter.submitList(pagedList);
        initRecycler2(pagedListAdapter);
    }

    private void initRecycler2(PagedListAdapter pagedListAdapter) {
        recycler = findViewById(R.id.recycler);
        recycler.setAdapter(pagedListAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
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
                    presenter.getUsers(adapter.getID());
                }
            }
        });

    }

    public String getId() {
        return adapter.getID();
    }

    private void initToolbar() {
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.app_name));
        initUpButton();
    }


    private void initUpButton() {
        ImageButton backButton = findViewById(R.id.button_up);
        backButton.setOnClickListener(v -> scrollUp());
        backButton.setRotation(-90);
    }

    private void scrollUp() {
        recycler.smoothScrollToPosition(0);
    }
}
