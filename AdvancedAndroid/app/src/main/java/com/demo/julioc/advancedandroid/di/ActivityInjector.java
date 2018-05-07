package com.demo.julioc.advancedandroid.di;

import android.app.Activity;
import android.content.Context;

import com.demo.julioc.advancedandroid.base.BaseActivity;
import com.demo.julioc.advancedandroid.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

public class ActivityInjector {

    private final Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;
    private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();

    @Inject
    ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors){
        this.activityInjectors = activityInjectors;
    }

    void inject(Activity aActivity)
    {
        if ( !(aActivity instanceof BaseActivity) )
            throw new IllegalArgumentException("Activity must be extended BaseActivity");

        String instanceId = ((BaseActivity) aActivity).getInstanceId();
        if (cache.containsKey(instanceId)) {
            //noinspection unchecked
            ((AndroidInjector<Activity>)cache.get(instanceId)).inject(aActivity);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Activity> injectorFactory = (AndroidInjector.Factory<Activity>) activityInjectors.get(aActivity.getClass()).get();
        AndroidInjector<Activity> injector = injectorFactory.create(aActivity);
        cache.put(instanceId, injector);
        injector.inject(aActivity);
    }

    void clear(Activity aActivity){

        if ( !(aActivity instanceof BaseActivity) )
            throw new IllegalArgumentException("Activity must be extended BaseActivity");

        cache.remove(((BaseActivity)aActivity).getInstanceId());
    }

    static ActivityInjector get(Context aContext){
        return ((MyApplication)aContext.getApplicationContext()).getActivityInjector();
    }
}
