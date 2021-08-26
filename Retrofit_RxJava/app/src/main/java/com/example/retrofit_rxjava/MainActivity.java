package com.example.retrofit_rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    RecyclerView recyclerView;
    SimpleInterface simpleInterface;
    List<MyData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Retrofit retrofit = RetrofitClient.getInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        simpleInterface = retrofit.create(SimpleInterface.class);

        fetchData();
    }

    private void fetchData() {
        disposable.add(
                simpleInterface.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyArray>() {
                    @Override
                    public void accept(MyArray myData) throws Exception {
                        displayData(myData);
                    }
                }));
    }

    private void displayData(MyArray myData) {
        data = myData.data;
        MyAdapter myAdapter = new MyAdapter(this, data);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose(); // don't send events once the activity is destroyed
    }
}