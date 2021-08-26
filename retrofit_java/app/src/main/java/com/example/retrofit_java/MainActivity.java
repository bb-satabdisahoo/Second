package com.example.retrofit_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SimpleInterface simpleInterface = retrofit.create(SimpleInterface.class);

        Call<Array> call = simpleInterface.getUserList();

        call.enqueue(new Callback<Array>() {
            @Override
            public void onResponse(Call<Array> call, Response<Array> response) {
                if(response.isSuccessful()){
                    Log.e("Successful", response.body().data.toString());
                    data = response.body().data;

                    Adapter adapter = new Adapter(data);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<Array> call, Throwable t) {
                Log.e("FAILURE", "Error in Fetching", t);
            }
        });
    }
}
