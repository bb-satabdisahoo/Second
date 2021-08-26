package com.example.roomdatabase_java;

import com.example.roomdatabase_java.db.UserEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MyArray {
    @SerializedName("data")
    List<UserEntity> data;
}
