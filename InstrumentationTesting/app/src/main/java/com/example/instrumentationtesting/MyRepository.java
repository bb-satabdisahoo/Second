package com.example.instrumentationtesting;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.instrumentationtesting.db.RoomAppDb;
import com.example.instrumentationtesting.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {

    private RoomAppDb database;
    private LiveData<List<UserEntity>> getAllUser;
    ArrayList<UserEntity> data;

    public  MyRepository(Application application){
        database = RoomAppDb.getDbInstance(application);
        getAllUser = database.userDao().getAllUsers();
    }

    public  LiveData<List<UserEntity>> getGetAllUser(){
        return getAllUser;
    }

    public void insert(UserEntity userEntity){
        database.userDao().insertUser(userEntity);
    }

    public void delete(UserEntity userEntity) {
        database.userDao().delete(userEntity);
    }
}
