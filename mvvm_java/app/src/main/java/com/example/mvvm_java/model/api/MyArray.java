package com.example.mvvm_java.model.api;

import com.example.mvvm_java.model.UserEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MyArray {
    @SerializedName("data")
    public
    ArrayList<UserEntity> data;
}
