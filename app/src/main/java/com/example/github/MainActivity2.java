package com.example.github;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github.databinding.ActivityMain2Binding;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class MainActivity2 extends AppCompatActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final IMainViewModel viewModel = new MainViewModel();
    private ViewBinding binding;
    private GithubAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialLoading();
        initRecycler();
        disposables.add(viewModel.getUsersObservable().subscribe(this::handleUsers));
    }

    private void initRecycler() {
        final RecyclerView recycler = getCastedBinding().recycler;
        adapter = new GithubAdapter();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadNextPage(adapter.getID());
                }
            }
        });

    }

    private void initialLoading() {
        viewModel.loadNextPage("1");
    }

    private void handleUsers(List<UserData> users) {
            adapter.addUsers(users);
    }

    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }

    private ActivityMain2Binding getCastedBinding() {
        return (ActivityMain2Binding) binding;
    }
}