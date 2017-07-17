package com.haibuzou.baselib;

import android.app.Application;

/**
 * Created by hywin on 2017/7/17.
 */

public class BaseApplication extends Application {

    public static BaseApplication baseContext;

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = this;
    }
}
