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

import com.example.github.Corona.CoronaListAdapter;
import com.example.github.Corona.CoronaPresenter;
import com.example.github.Corona.DoubleRegion;
import com.example.github.Room.UserData;
import com.example.github.Room.UserStorage;
import com.example.github.modules.network.models.Region;
import com.example.github.Room.DataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter presenter;
    private GithubAdapter adapter;
    private CoronaListAdapter coronaListAdapter;
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
        presenter.onDestroy();
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
        coronaListAdapter.setItems(regions);
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
                    Log.i("STATE_CHANGE", String.valueOf(newState));
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
    }

}
