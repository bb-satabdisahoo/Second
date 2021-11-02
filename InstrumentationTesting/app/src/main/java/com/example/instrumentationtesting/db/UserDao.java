package com.example.instrumentationtesting.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY unique_id ASC")
    LiveData<List<UserEntity>> getAllUsers();

    @Insert
    void insertUser(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);
}
