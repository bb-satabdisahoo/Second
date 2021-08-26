package com.example.mvvm_java.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvm_java.MyApplication;
import com.example.mvvm_java.model.UserEntity;


@Database(entities = {UserEntity.class}, version = 1)
public abstract class RoomAppDb extends RoomDatabase {

    public abstract UserDao userDao();

    private static RoomAppDb INSTANCE;

    public static RoomAppDb getDbInstance(){

        if(INSTANCE == null){
            synchronized (RoomAppDb.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(MyApplication.get(), RoomAppDb.class, "DB Name")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}