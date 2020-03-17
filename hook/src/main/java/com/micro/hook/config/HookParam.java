package com.micro.hook.config;

import android.app.Application;
import android.content.Context;

/**
 * created by kilin on 19-12-16 上午11:39
 */
public class HookParam {
    private final boolean isFirstApplication;
    private ClassLoader classLoader;
    private final Context context;

    public HookParam(boolean isFirstApplication, Context context, ClassLoader classLoader) {
        this.isFirstApplication = isFirstApplication;
        this.context = context;
        this.classLoader = classLoader;
    }

    public boolean isFirstApplication() {
        return isFirstApplication;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    private Application application;

    public void setApplication(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public Context getContext() {
        return context;
    }

    private Hook hook;

    public Hook getHook() {
        return hook;
    }

    public void setHook() {
        if (getClassLoader() == null) {
            this.hook = null;
        } else {
            this.hook = Hook.getInstance(getClassLoader());
        }
    }

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}