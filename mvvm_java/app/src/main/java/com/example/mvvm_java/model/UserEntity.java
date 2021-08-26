package com.example.mvvm_java.model;

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

    public UserEntity(int id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}