package com.hazemelmamlouk.paggingmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hazemelmamlouk.paggingmvvm.adapter.UserAdapter;
import com.hazemelmamlouk.paggingmvvm.model.User;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        final UserAdapter adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserViewModel itemViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        itemViewModel.userPagedList.observe(this, new Observer<PagedList<User>>() {
            @Override public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
