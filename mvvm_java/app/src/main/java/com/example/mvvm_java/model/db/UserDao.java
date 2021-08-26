package com.example.mvvm_java.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.mvvm_java.model.UserEntity;
import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY unique_id ASC")
    //List<UserEntity> getAllUsers();
    LiveData<List<UserEntity>> getAllUsers();

    @Insert
    //void insertUser(UserEntity userEntity);
    void insertUser(List<UserEntity> userEntities);

    @Delete
    //void delete(UserEntity userEntity);
    void delete(List<UserEntity> userEntities);
}
