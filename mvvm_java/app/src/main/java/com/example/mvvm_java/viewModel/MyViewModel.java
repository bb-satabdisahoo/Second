package com.example.mvvm_java.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_java.model.UserEntity;
import com.example.mvvm_java.model.api.MyArray;
import com.example.mvvm_java.model.api.RetroInstance;
import com.example.mvvm_java.model.api.SimpleInterface;
import com.example.mvvm_java.model.db.RoomAppDb;
import com.example.mvvm_java.model.repository.MyRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    /*public void insertUser(List<UserEntity> list){
        myRepository.insert(list);
    }*/
}
