package com.example.mvvm_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvm_java.R;
import com.example.mvvm_java.model.UserEntity;
import com.example.mvvm_java.viewModel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LiveData<List<UserEntity>> userEntityList;
    private List<UserEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        //myViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);
         userEntityList = myViewModel.getGetAllUser();

        myViewModel.getGetAllUser().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                Toast.makeText(MainActivity.this, "Working",Toast.LENGTH_SHORT).show();
                list = userEntityList.getValue(); // get the live data value to use in recycler view.
                recyclerView.setAdapter(new RecyclerViewAdapter(list));
            }
        });
    }
}
/**/