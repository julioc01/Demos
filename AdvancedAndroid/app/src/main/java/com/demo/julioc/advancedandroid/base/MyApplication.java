package com.demo.julioc.advancedandroid.base;

import android.app.Application;


public class MyApplication extends Application {

    private ApplicationComponent fComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        fComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
