package com.example.retrofit_java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SimpleInterface {

    @GET("users")
    Call<Array> getUserList();
}
