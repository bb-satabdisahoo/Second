package com.example.instrumentationtesting;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.instrumentationtesting.db.UserEntity;
import com.example.instrumentationtesting.util.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends DatabaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    //Insert Read Delete
    @Test
    public void insertReadDelete() throws Exception{

        UserEntity entity = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity.setUnique_id(1);

        // insert
        getUserDao().insertUser(entity);

        // read
        LiveDataTestUtil<List<UserEntity>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<UserEntity> insertedUsers = liveDataTestUtil.getValue(getUserDao().getAllUsers());

        assertNotNull(insertedUsers);

        assertEquals(entity.getName(), insertedUsers.get(0).getName());
        assertEquals(entity.getAddress(), insertedUsers.get(0).getAddress());

        entity.setUnique_id(insertedUsers.get(0).getUnique_id());
        assertEquals(entity, insertedUsers.get(0));

        // delete
        getUserDao().delete(entity);

        // confirm the database is empty
        insertedUsers = liveDataTestUtil.getValue(getUserDao().getAllUsers());
        assertEquals(0, insertedUsers.size());
    }

    @Test(expected = SQLiteConstraintException.class)
    public void insert_nullName_throwSQLiteConstraintException() throws Exception{

        UserEntity entity = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity.setUnique_id(1);

        entity.setName(null);

        // insert
        getUserDao().insertUser(entity);
    }

    @Test(expected = SQLiteConstraintException.class)
    public void insert_nullAddress_throwSQLiteConstraintException() throws Exception{

        UserEntity entity = new UserEntity("Satabdi","Bhadrak, Odisha");
        entity.setUnique_id(1);

        entity.setAddress(null);

        // insert
        getUserDao().insertUser(entity);
    }
}
