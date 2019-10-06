package com.hazemelmamlouk.paggingmvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hazemelmamlouk.paggingmvvm.model.User;
import com.hazemelmamlouk.paggingmvvm.network.UserDataSource;
import com.hazemelmamlouk.paggingmvvm.network.UserDataSourceFactory;

public class UserViewModel extends ViewModel {
    public LiveData<PagedList<User>> userPagedList;
    private LiveData<UserDataSource> liveDataSource;
    public UserViewModel() {
        init();
    }
    private void init() {
        UserDataSourceFactory itemDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(UserDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}
