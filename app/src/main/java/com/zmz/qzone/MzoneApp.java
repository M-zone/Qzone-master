package com.zmz.qzone;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mzone on 2016/11/16.
 */

public class MzoneApp extends Application {
    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        MzoneApp.CONTEXT = getApplicationContext();
    }

    public static Context getAppContext() {
        return CONTEXT;
    }

}
