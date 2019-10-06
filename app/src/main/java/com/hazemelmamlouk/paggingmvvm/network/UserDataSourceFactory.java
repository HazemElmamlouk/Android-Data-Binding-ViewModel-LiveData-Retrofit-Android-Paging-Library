package com.hazemelmamlouk.paggingmvvm.network;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.hazemelmamlouk.paggingmvvm.model.User;

public class UserDataSourceFactory extends DataSource.Factory<Long, User> {
    public MutableLiveData<UserDataSource> userLiveDataSource=new MutableLiveData<>();
    @Override public DataSource<Long, User> create() {
        UserDataSource userDataSource = new UserDataSource();
        userLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }
}
