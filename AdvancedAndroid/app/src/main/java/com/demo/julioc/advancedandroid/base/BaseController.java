package com.demo.julioc.advancedandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.demo.julioc.advancedandroid.di.Injector;

public abstract class BaseController extends Controller {

    private boolean injected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {

        //This happens because controller instances are retained across config changes, so this
        // method can be called more than once. This makes sure we don't waste any time injecting
        //more than once, though technically it wouldn't change functionality.
        if ( !injected ) {
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }
}
