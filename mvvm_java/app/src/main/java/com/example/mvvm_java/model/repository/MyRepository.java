package com.example.mvvm_java.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_java.model.UserEntity;
import com.example.mvvm_java.model.api.MyArray;
import com.example.mvvm_java.model.api.RetroInstance;
import com.example.mvvm_java.model.api.SimpleInterface;
import com.example.mvvm_java.model.db.RoomAppDb;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyRepository {

    private RoomAppDb database;
    private LiveData<List<UserEntity>> getAllUser;
    ArrayList<UserEntity> data;

    public  MyRepository(Application application){
        database = RoomAppDb.getDbInstance();
        getAllUser = database.userDao().getAllUsers();
    }

    public  LiveData<List<UserEntity>> getGetAllUser(){
        SimpleInterface simpleInterface = RetroInstance.getRetroClient().create(SimpleInterface.class);
        Call<MyArray> call = simpleInterface.getUserList();
        call.enqueue(new Callback<MyArray>() {
            @Override
            public void onResponse(Call<MyArray> call, Response<MyArray> response) {
                if(response.isSuccessful()){
                    data = response.body().data;
                    database.userDao().insertUser(data);
                }
            }

            @Override
            public void onFailure(Call<MyArray> call, Throwable t) {
                Log.e("FAILURE", "Error in Fetching", t);
            }
        });
            return getAllUser;
    }

   /* public void insert(List<UserEntity> list){
        database.userDao().insertUser(data);
    }*/

}
