package com.demo.julioc.advancedandroid.base;

import android.app.Application;

import com.demo.julioc.advancedandroid.di.ActivityInjector;
import com.demo.julioc.advancedandroid.di.Injector;

import javax.inject.Inject;


public class MyApplication extends Application {

    private ApplicationComponent fComponent;

    @Inject
    ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        fComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        fComponent.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}

