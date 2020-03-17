package com.micro.root.application;

import android.app.Application;

/**
 * @CreateDate: 19-9-16
 */
public class BaseApplication extends Application {

    private static BaseApplication mBaseApplication;

    public static void setBaseApplication(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setBaseApplication(this);
    }
}