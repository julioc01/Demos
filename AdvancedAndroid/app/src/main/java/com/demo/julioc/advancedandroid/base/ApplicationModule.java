package com.demo.julioc.advancedandroid.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application aApplication) {

        this.application = aApplication;
    }

    @Provides
    Context provideApplicationContext() {
        return application;
    }
}
