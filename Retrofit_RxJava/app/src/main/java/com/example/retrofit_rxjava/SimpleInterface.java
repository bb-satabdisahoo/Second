package com.example.retrofit_rxjava;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SimpleInterface {
    @GET("users")
    //Call<MyArray> getUserList();

    Observable<MyArray> getUserList();
}
