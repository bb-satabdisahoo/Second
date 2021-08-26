package com.example.roomdatabase_java;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SimpleInterface {
    @GET("users")
    Call<MyArray> getUserList();
}
