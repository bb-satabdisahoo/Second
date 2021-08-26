package com.example.mvvm_java.model.api;

import com.example.mvvm_java.model.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SimpleInterface {
    @GET("users")
    Call<MyArray> getUserList();
}