package com.example.roomdatabase_java.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY unique_id ASC")
    List<UserEntity> getAllUsers();

    @Insert
    void insertUser(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);
}
