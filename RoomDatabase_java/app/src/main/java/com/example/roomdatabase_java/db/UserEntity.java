package com.example.roomdatabase_java.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "userinfo")
public class UserEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "unique_id")
    public int unique_id;

    @ColumnInfo(name = "Id")
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "Name")
    @SerializedName("name")
    public String name;

    @ColumnInfo(name = "Email")
    @SerializedName("email")
    public String email;

    @ColumnInfo(name = "Gender")
    @SerializedName("gender")
    public String gender;

    @ColumnInfo(name = "Status")
    @SerializedName("status")
    public String status;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

}
