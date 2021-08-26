package com.example.mvvm_java;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static MyApplication get(){
        return Instance;
    }

}
