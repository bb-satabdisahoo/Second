package com.example.roomdatabase_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.roomdatabase_java.db.RoomAppDb;
import com.example.roomdatabase_java.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserEntity> data;
    RoomAppDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = RoomAppDb.getDbInstance(this.getApplicationContext());
        setRecyclerView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SimpleInterface simpleInterface = retrofit.create(SimpleInterface.class);

        Call<MyArray> call = simpleInterface.getUserList();

        call.enqueue(new Callback<MyArray>() {
            @Override
            public void onResponse(Call<MyArray> call, Response<MyArray> response) {
                if(response.isSuccessful()){
                    Log.e("Successful", response.body().data.toString());
                    data = response.body().data;

                    for(int i=0;i<data.size();i++)
                    {
                        UserEntity userEntity = data.get(i);
                        db.userDao().insertUser(userEntity);
                    }

                }

            }

            @Override
            public void onFailure(Call<MyArray> call, Throwable t) {
                Log.e("FAILURE", "Error in Fetching", t);
            }
        });
    }

    private void setRecyclerView() {
        List<UserEntity> userList = db.userDao().getAllUsers();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, userList);
        recyclerView.setAdapter(adapter);
    }
}
