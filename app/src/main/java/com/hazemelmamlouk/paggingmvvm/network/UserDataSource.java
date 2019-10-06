package com.hazemelmamlouk.paggingmvvm.network;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.hazemelmamlouk.paggingmvvm.model.User;
import com.hazemelmamlouk.paggingmvvm.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Long, User> {
    public static int PAGE_SIZE = 6;
    public static long FIRST_PAGE = 1;
    @Override public void loadInitial(@NonNull final LoadInitialParams<Long> params,
                                      @NonNull final LoadInitialCallback<Long, User> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = service.getUsers(FIRST_PAGE);
        call.enqueue(new Callback<UserResponse>() {
            @Override public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
                }
            }
            @Override public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params,
                           @NonNull final LoadCallback<Long, User> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = apiService.getUsers(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);
                }
            }
            @Override public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, User> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = service.getUsers(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, params.key + 1);
                }
            }
            @Override public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
}
