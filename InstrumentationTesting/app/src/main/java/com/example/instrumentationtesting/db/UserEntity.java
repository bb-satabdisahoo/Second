package com.example.instrumentationtesting.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "userinfo")
public class UserEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "unique_id")
    public int unique_id;

    @NonNull
    @ColumnInfo(name = "Name")
    public String name;

    @NonNull
    @ColumnInfo(name = "Address")
    public String address;

    public UserEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    protected UserEntity(Parcel in) {
        unique_id = in.readInt();
        name = in.readString();
        address = in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        UserEntity entity = (UserEntity) obj;
        return entity.getUnique_id() == getUnique_id() && entity.getName().equals(getName()) && entity.getAddress().equals(getAddress());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(unique_id);
        dest.writeString(name);
        dest.writeString(address);

    }
}
