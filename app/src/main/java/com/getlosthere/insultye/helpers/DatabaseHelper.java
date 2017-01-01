package com.getlosthere.insultye.helpers;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.util.ReflectionUtils;

/**
 * Created by violetaria on 12/18/16.
 */

public class DatabaseHelper {
    private Context context;

    public DatabaseHelper(Context context){
        this.context = context;
    }

    public void resetDatabase() {
        ActiveAndroid.dispose();
        String aaName = ReflectionUtils.getMetaData(context, "AA_DB_NAME");

        if (aaName == null) {
            aaName = "insultye.db";
        }
        context.deleteDatabase(aaName);
        ActiveAndroid.initialize(context);
    }
}

