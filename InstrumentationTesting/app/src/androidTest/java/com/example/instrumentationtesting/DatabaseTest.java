package com.example.instrumentationtesting;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.instrumentationtesting.db.RoomAppDb;
import com.example.instrumentationtesting.db.UserDao;

import org.junit.After;
import org.junit.Before;

public class DatabaseTest {

    private RoomAppDb database;

    public UserDao getUserDao(){
        return database.userDao();
    }

    @Before
    public void init(){
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                RoomAppDb.class
        ).build();
    }

    @After
    public void finish(){
        database.close();
    }
}
