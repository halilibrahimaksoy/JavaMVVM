package com.haksoy.mvvmdemo.model;

import androidx.lifecycle.MutableLiveData;

import com.haksoy.mvvmdemo.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();
    private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

    public UserRepository() {
    }

    public MutableLiveData<List<User>> getMutableLiveData() {
        if (mutableLiveData.getValue() == null || mutableLiveData.getValue().isEmpty()) {
            Call<UserListResponse> call = RetrofitClient.getUserList(50);
            call.enqueue(new Callback<UserListResponse>() {
                @Override
                public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                    users = (ArrayList<User>) response.body().getResults();
                    mutableLiveData.setValue(users);
                }

                @Override
                public void onFailure(Call<UserListResponse> call, Throwable t) {

                }
            });
        }

        return mutableLiveData;
    }

    public MutableLiveData<List<User>> addMutableLiveData() {
        Call<UserListResponse> call = RetrofitClient.getUserList(10);
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                users.addAll((ArrayList<User>) response.body().getResults());
                mutableLiveData.setValue(users);
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }

}
