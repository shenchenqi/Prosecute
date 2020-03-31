package com.micro.root.mvp;

import android.os.Handler;
import android.os.Looper;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:43
 */
public abstract class BasePresenter<Interface extends BaseInterface> {

    protected Interface clazz;
    protected Handler handler;

    public void setClass(Interface clazz) {
        if (clazz == null) {
            throw new NullPointerException("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    public Interface getClazz() {
        return clazz;
    }

    public abstract void onAttached();

    public void onDetached() {
        if (clazz != null) {
            clazz = null;
        }
    }

    protected void setHandlerPost(long time, boolean isLooper, Runnable runnable) {
        if (isLooper) {
            handler = new Handler(Looper.getMainLooper());
        } else {
            handler = new Handler();
        }
        if (time > 0) {
            handler.postDelayed(runnable, time);
        } else {
            handler.post(runnable);
        }
    }
}