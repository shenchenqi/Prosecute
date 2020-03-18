package com.micro.hook.setup;

import android.content.Context;

/**
 * created by kilin on 20-3-18 上午9:36
 */
public abstract class SetupPresenter<Inter extends SetupInter> {

    private Inter clazz;

    void setClazz(Inter clazz) throws Throwable {
        if (clazz == null) {
            throw new Throwable("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    public Inter getClazz() {
        return clazz;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void onAttached();
}