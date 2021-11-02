package com.example.instrumentationtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instrumentationtesting.db.UserEntity;

import java.util.List;

public class show extends AppCompatActivity implements RecyclerViewAdapter.ItemClicked {

    MyViewModel myViewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myViewModel.getGetAllUser().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                setRecyclerView();
            }
        });
    }

    private void setRecyclerView() {
        LiveData<List<UserEntity>> userList = myViewModel.getGetAllUser();
        List<UserEntity> user = userList.getValue();
        adapter = new RecyclerViewAdapter(this, user, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateClicked(UserEntity userEntity) {

    }

    @Override
    public void deleteClicked(UserEntity userEntity) {
        myViewModel.deleteUser(userEntity);
        myViewModel.getGetAllUser();
    }
}