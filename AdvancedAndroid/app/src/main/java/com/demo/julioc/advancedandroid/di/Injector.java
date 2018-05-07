package com.demo.julioc.advancedandroid.di;

import android.app.Activity;
import android.util.Log;

import com.bluelinelabs.conductor.Controller;

public class Injector {

    private Injector(){

    }

    public static void inject(Activity aActivity){
        ActivityInjector.get(aActivity).inject(aActivity);
    }

    public static void clearComponent(Activity aActivity) {

        Log.i("TAG", "Injected " + aActivity.getClass().getName());
        ActivityInjector.get(aActivity).clear(aActivity);
    }

    public static void inject(Controller controller) {
        ScreenInjector.get(controller.getActivity()).inject(controller);
    }

    public static void clearComponent(Controller aController) {
        ScreenInjector.get(aController.getActivity()).clear(aController);
    }
}
