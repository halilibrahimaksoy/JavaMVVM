package com.haksoy.veloxitydemo.service;

import com.haksoy.veloxitydemo.model.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
    @GET("api/")
    Call<UserListResponse> getAnnounce(@Query("nat") String nat, @Query("results") Integer count);
}
