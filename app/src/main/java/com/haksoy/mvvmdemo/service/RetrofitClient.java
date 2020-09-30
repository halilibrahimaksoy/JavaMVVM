package com.haksoy.mvvmdemo.service;

import com.haksoy.mvvmdemo.model.UserListResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Call<UserListResponse> getUserList(int count) {
        UserApi api = getRetrofitInstance().create(UserApi.class);
        return api.getAnnounce("tr", count);
    }
}
