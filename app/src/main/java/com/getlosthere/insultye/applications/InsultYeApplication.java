package com.getlosthere.insultye.applications;

import android.content.res.Resources;

import com.facebook.stetho.Stetho;

/**
 * Created by violetaria on 12/11/16.
 */

public class InsultYeApplication extends com.activeandroid.app.Application {
    public static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources();
        Stetho.initializeWithDefaults(this);
    }
}

