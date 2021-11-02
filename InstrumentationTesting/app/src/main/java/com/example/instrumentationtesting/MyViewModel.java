package com.example.instrumentationtesting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instrumentationtesting.db.UserEntity;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    private LiveData<List<UserEntity>> getAllUsers;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        getAllUsers = myRepository.getGetAllUser();
    }

    public LiveData<List<UserEntity>> getGetAllUser(){
        return getAllUsers;
    }

    public void insertUser(UserEntity userEntity){
        myRepository.insert(userEntity);
        getGetAllUser();
    }

    public void deleteUser(UserEntity userEntity){
        myRepository.delete(userEntity);
        getGetAllUser();
    }
}