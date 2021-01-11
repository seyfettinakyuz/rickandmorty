package com.akyuz.rickandmorty;


import android.app.Activity;
import android.app.Application;

import com.akyuz.rickandmorty.di.components.DaggerAppComponent;
import com.akyuz.rickandmorty.helper.PrefUtils;
import com.akyuz.rickandmorty.helper.ResourceManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends Application implements HasActivityInjector {
    private static BaseApplication sInstance;


    public static BaseApplication getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(BaseApplication app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        initializePrefUtils();
        initializeResourceManager();
        setInstance(this);
    }

    private void initializeResourceManager() {
        ResourceManager.init(this);
    }

    private void initializePrefUtils() {
        PrefUtils.init(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}

