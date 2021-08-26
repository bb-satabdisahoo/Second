package com.example.roomdatabase_java.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class RoomAppDb extends RoomDatabase {

    public abstract UserDao userDao();

    private static RoomAppDb INSTANCE;

    public static RoomAppDb getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomAppDb.class, "DB Name")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
