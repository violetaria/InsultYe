package com.getlosthere.insultye.applications;

import android.content.res.Resources;

import com.facebook.stetho.Stetho;
import com.getlosthere.insultye.helpers.DatabaseHelper;

/**
 * Created by violetaria on 12/11/16.
 */

public class InsultYeApplication extends com.activeandroid.app.Application { // com.orm.SugarApp {
    public static Resources resources;
    private DatabaseHelper myDB;

    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources();
        Stetho.initializeWithDefaults(this);

//        myDB = new DatabaseHelper(getApplicationContext());
//
//        try {
//            myDB.createDataBase();
//        }
//        catch(IOException e)
//        {
//            // do nothing
//        }

    }
}

