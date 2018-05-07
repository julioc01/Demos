package com.demo.julioc.advancedandroid.base;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication aMyApplication);
}
