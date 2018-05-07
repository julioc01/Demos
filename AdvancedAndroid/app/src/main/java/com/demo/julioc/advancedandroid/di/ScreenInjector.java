package com.demo.julioc.advancedandroid.di;

import android.app.Activity;
import android.content.Context;

import com.bluelinelabs.conductor.Controller;
import com.demo.julioc.advancedandroid.base.BaseActivity;
import com.demo.julioc.advancedandroid.base.BaseController;
import com.demo.julioc.advancedandroid.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {

    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors){
        this.screenInjectors = screenInjectors;
    }

    void inject(Controller aController)
    {
        if ( !(aController instanceof BaseController) )
            throw new IllegalArgumentException("Controller must be extended BaseController");

        String instanceId = ((BaseController) aController).getInstanceId();
        if (cache.containsKey(instanceId)) {
            cache.get(instanceId).inject(aController);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectorFactory = (AndroidInjector.Factory<Controller>) screenInjectors.get(aController.getClass()).get();
        AndroidInjector<Controller> injector = injectorFactory.create(aController);
        cache.put(instanceId, injector);
        injector.inject(aController);
    }

    void clear(Controller aController){

        if ( !(aController instanceof Controller) )
            throw new IllegalArgumentException("Controller must be extended BaseController");

        cache.remove(aController.getInstanceId());
    }

    static ScreenInjector get(Activity aActivity){
        return ((BaseActivity)aActivity).getScreenInjector();
    }
}
