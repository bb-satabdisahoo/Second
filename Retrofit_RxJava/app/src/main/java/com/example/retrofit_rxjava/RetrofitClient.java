package com.example.retrofit_rxjava;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit ourInstance;

    public static Retrofit getInstance(){
        if(ourInstance==null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://gorest.co.in/public/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
            return ourInstance;
    }
    private RetrofitClient(){

    }
}
